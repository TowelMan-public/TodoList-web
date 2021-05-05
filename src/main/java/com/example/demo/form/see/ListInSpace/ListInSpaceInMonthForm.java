package com.example.demo.form.see.ListInSpace;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListInSpaceInMonthForm {
	//この中にListCountInDayを必ず7こ作ること
	private List<ListCountInDay> listCountinWeek;
	
	public static final ListCountInDay emptyListCountDay = new ListCountInDay();
	
	@Data
	public static class ListCountInDay{
		private String day;
		private String assignmentDate;
		private Integer listCount;
	}
}
