package org.example.kushousebackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.JoinDto;
import org.example.kushousebackend.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> addMember(@RequestBody JoinDto joinDto){

        Boolean joinSuccess = memberService.addMember(joinDto);
        if(!joinSuccess){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원 가입 실패");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");
    }


}
