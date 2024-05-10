package org.example.kushousebackend.service;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.JoinDto;
import org.example.kushousebackend.data.entity.MemberEntity;
import org.example.kushousebackend.data.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean addMember(JoinDto joinDto){

        Boolean isExist = memberRepository.existsByUsername(joinDto.getUsername());
        if(isExist) return false;

        MemberEntity entity = new MemberEntity();
        entity.setUsername(joinDto.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        entity.setNickname(joinDto.getNickname());
        entity.setRole("ROLE_USER");
        memberRepository.save(entity);
        return true;
    }
}
