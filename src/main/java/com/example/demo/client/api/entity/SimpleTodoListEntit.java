package com.example.demo.client.api.entity;

import java.util.Date;

import lombok.Data;

@Data
public class SimpleTodoListEntit {
	private Integer spaceId;
    private Integer userId;
    private Integer listId;
    private String listName;
    private Date listDate;
}
