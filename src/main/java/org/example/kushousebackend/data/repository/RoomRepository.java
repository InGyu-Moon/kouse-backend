package org.example.kushousebackend.data.repository;

import org.example.kushousebackend.data.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    RoomEntity getRoomEntityByRoomId(Long id);
}
