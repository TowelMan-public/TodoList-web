package com.example.demo.client.api.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ListEntity {
	private Integer listId;
	private String listName;
	private Date listDate;
	private Boolean isAlarm;
	private Boolean isEnabled;
	private Date updateTimestamp;
}
