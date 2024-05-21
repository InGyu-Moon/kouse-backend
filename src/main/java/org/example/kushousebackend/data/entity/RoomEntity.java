package org.example.kushousebackend.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROOM_INFO")
@Getter
@Setter
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;
    private String roomAddress;
    private int roomDeposit;
    private String roomPaymentType;
    private int roomCost;
    private String roomOption;
    private String roomDetail;
    private String roomLocation;
    private String imgUrl;
    private int imgAmount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;


}
