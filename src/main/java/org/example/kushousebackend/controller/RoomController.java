package org.example.kushousebackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.RoomDto;
import org.example.kushousebackend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        return ResponseEntity.ok().body(roomService.getAllRooms());
    }
    @GetMapping("/room/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok().body(roomService.getRoomById(id));
    }

    @PostMapping("/room")
    public ResponseEntity<String> insertRoom(
            @RequestPart RoomDto roomDto,
            @RequestPart(value = "roomImg",required = false) ArrayList<MultipartFile> imgArr,
            HttpServletRequest request) {

        String path=request.getSession().getServletContext().getRealPath("/image");

        if(imgArr==null){
            roomDto.setImgAmount(0);
            roomDto.setImgUrl(null);
        } else{
            String uuid= UUID.randomUUID().toString();
            for(int i=0;i<imgArr.size();i++){
                MultipartFile img = imgArr.get(i);
                try {
                    String realPath = path + "\\" + uuid + "_" + (i + 1) + "."
                            + img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf('.') + 1);
                    img.transferTo(new File(realPath));
                } catch (Exception e) {
                    System.out.println("file upload error = " + e);
                }
            }
            roomDto.setImgAmount(imgArr.size());
            roomDto.setImgUrl(uuid);
        }

        //TODO room Entity에 imgUrl,imgAmount 추가
        roomService.insertRoom(roomDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("생성완료");
    }
}
