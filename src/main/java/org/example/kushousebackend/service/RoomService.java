package org.example.kushousebackend.service;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.RoomDto;
import org.example.kushousebackend.data.entity.MemberEntity;
import org.example.kushousebackend.data.entity.RoomEntity;
import org.example.kushousebackend.data.repository.MemberRepository;
import org.example.kushousebackend.data.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    public void insertRoom(RoomDto roomDto) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomName(roomDto.getRoomName());
        roomEntity.setRoomAddress(roomDto.getRoomAddress());
        roomEntity.setRoomDeposit(roomDto.getRoomDeposit());
        roomEntity.setRoomPaymentType(roomDto.getRoomPaymentType());
        roomEntity.setRoomCost(roomDto.getRoomCost());
        roomEntity.setRoomOption(roomDto.getRoomOption());
        roomEntity.setRoomDetail(roomDto.getRoomDetail());

        MemberEntity memberEntity = memberRepository.findById(roomDto.getMemberId()).orElse(null);
        roomEntity.setMember(memberEntity);

        roomRepository.save(roomEntity);
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
        return dto;
    }
}
