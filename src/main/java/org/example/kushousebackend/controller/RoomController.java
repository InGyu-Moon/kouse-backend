package org.example.kushousebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.RoomDto;
import org.example.kushousebackend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        return ResponseEntity.ok().body(roomService.getAllRooms());
    }

    @PostMapping("/room")
    public ResponseEntity<String> insertRoom(@RequestBody RoomDto roomDto) {
        roomService.insertRoom(roomDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("생성완료");
    }
}
