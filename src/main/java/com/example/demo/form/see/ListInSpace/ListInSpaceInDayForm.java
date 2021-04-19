package com.example.demo.form.see.ListInSpace;

import java.util.List;

import lombok.Data;

@Data
public class ListInSpaceInDayForm {
	private Integer contentCount;
	private Integer listId;
	private String listTime;
	private boolean isAffterDateList;
	private String listName;
	private List<ContentForm> contentsInList;
	
	@Data
	public static class ContentForm{
		private String contentTitle;
		private Integer contentId;
	}
}
