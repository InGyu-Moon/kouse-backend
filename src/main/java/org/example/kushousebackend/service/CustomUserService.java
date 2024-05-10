package org.example.kushousebackend.service;

import lombok.RequiredArgsConstructor;
import org.example.kushousebackend.data.dto.CustomUserDetails;
import org.example.kushousebackend.data.entity.MemberEntity;
import org.example.kushousebackend.data.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberEntity data = memberRepository.findByUsername(username);
        if(data != null){
            return new CustomUserDetails(data);
        }

        return null;
    }
}
