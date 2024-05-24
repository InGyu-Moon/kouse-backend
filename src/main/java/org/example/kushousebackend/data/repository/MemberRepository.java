package org.example.kushousebackend.data.repository;

import org.example.kushousebackend.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Boolean existsByUsername(String username);
    MemberEntity findByUsername(String username);
}
