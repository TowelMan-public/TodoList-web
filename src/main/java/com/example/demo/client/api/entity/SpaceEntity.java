package com.example.demo.client.api.entity;

import java.util.Date;

import lombok.Data;

@Data
public class SpaceEntity {
	private Integer spaceId;
    private String spaceName;
    private Integer scopeId;
    private Boolean isSimple;
    private Boolean isEnable;
    private Date updateTimestamp;
}