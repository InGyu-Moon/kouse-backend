package org.example.kushousebackend.data.repository;

import org.example.kushousebackend.data.entity.ImgEntity;
import org.example.kushousebackend.data.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends JpaRepository<ImgEntity, Long>{
    List<ImgEntity> findByRoom(RoomEntity room);
}
