package org.example.kushousebackend.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
    private Long roomId;
    private String roomName;
    private String roomAddress;
    private int roomDeposit;
    private String roomPaymentType;
    private int roomCost;
    private String roomOption;
    private String roomDetail;
    private Long memberId;
    private String roomLocation;
    private boolean hasImg;
}
