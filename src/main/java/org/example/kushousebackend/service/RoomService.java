package org.example.kushousebackend.service;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.ImgDto;
import org.example.kushousebackend.data.dto.RoomDto;
import org.example.kushousebackend.data.entity.ImgEntity;
import org.example.kushousebackend.data.entity.MemberEntity;
import org.example.kushousebackend.data.entity.RoomEntity;
import org.example.kushousebackend.data.repository.ImgRepository;
import org.example.kushousebackend.data.repository.MemberRepository;
import org.example.kushousebackend.data.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ImgRepository imgRepository;

    public void insertRoom(RoomDto roomDto, ArrayList<MultipartFile> imgArr, String path) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomName(roomDto.getRoomName());
        roomEntity.setRoomAddress(roomDto.getRoomAddress());
        roomEntity.setRoomDeposit(roomDto.getRoomDeposit());
        roomEntity.setRoomPaymentType(roomDto.getRoomPaymentType());
        roomEntity.setRoomCost(roomDto.getRoomCost());
        roomEntity.setRoomOption(roomDto.getRoomOption());
        roomEntity.setRoomDetail(roomDto.getRoomDetail());
        roomEntity.setRoomLocation(roomDto.getRoomLocation());

        MemberEntity memberEntity = memberRepository.findById(roomDto.getMemberId()).orElse(null);
        roomEntity.setMember(memberEntity);
        roomEntity.setHasImg(roomDto.isHasImg());
        roomRepository.save(roomEntity);

        if (roomDto.isHasImg()) {
            insertImg(imgArr, path, roomEntity.getRoomId());
        }
    }

    public void insertImg(ArrayList<MultipartFile> imgArr, String path, Long roomId) {
        RoomEntity roomEntity = roomRepository.getRoomEntityByRoomId(roomId);
        String uuid= UUID.randomUUID().toString();
        for(int i=0;i<imgArr.size();i++){
            MultipartFile img = imgArr.get(i);
            try {
                String fileName = uuid + "_" + (i + 1) + "." + img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf('.') + 1);

//                String realPath = path + "\\" + fileName;
                String realPath = path + "/" + fileName;

//                System.out.println("path = " + path);
//                System.out.println("realPath = " + realPath);

                img.transferTo(new File(realPath));
                ImgEntity imgEntity = new ImgEntity();
                imgEntity.setImgName(fileName);
                imgEntity.setRoom(roomEntity);
                imgRepository.save(imgEntity);
            } catch (Exception e) {
                System.out.println("file upload error = " + e);
            }
        }
    }




    public RoomDto getRoomById(Long id){
        RoomEntity entity = roomRepository.getRoomEntityByRoomId(id);
        return convertToDTO(entity);
    }

    public List<RoomDto> getAllRooms() {
        List<RoomEntity> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private RoomDto convertToDTO(RoomEntity roomEntity) {
        RoomDto dto = new RoomDto();
        dto.setRoomId(roomEntity.getRoomId());
        dto.setRoomName(roomEntity.getRoomName());
        dto.setRoomAddress(roomEntity.getRoomAddress());
        dto.setRoomDeposit(roomEntity.getRoomDeposit());
        dto.setRoomPaymentType(roomEntity.getRoomPaymentType());
        dto.setRoomCost(roomEntity.getRoomCost());
        dto.setRoomOption(roomEntity.getRoomOption());
        dto.setRoomDetail(roomEntity.getRoomDetail());
        dto.setRoomLocation(roomEntity.getRoomLocation());
        dto.setHasImg(roomEntity.isHasImg());
        return dto;
    }


    public List<String> getRoomImg(Long roomId) {
        RoomEntity room = roomRepository.getRoomEntityByRoomId(roomId);
        List<String> imgList = new ArrayList<>();
        imgRepository.findByRoom(room).forEach(img -> imgList.add(img.getImgName()));
        return imgList;
    }
}
