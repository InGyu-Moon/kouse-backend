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

        roomDto.setHasImg(imgArr != null);
        roomService.insertRoom(roomDto);

        if (imgArr != null) {
            roomService.insertImg(imgArr, path, roomDto.getRoomId());
        }


        return ResponseEntity.status(HttpStatus.CREATED).body("생성완료");
    }

}
