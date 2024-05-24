package org.example.kushousebackend.data.repository;

import org.example.kushousebackend.data.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<ImgEntity, Long>{
}
