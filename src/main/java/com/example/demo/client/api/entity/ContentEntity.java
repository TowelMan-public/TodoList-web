package com.example.demo.client.api.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ContentEntity {
	private Integer contentId;
	private Integer listId;
	private String title;
	private String contentText;
	private Boolean isEnabled;
	private Date updateTimestamp;
}
