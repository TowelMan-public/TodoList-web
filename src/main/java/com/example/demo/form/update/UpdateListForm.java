package com.example.demo.form.update;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.RegexpMessage;
import com.example.demo.utility.DateUtility;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateListForm {
	DateUtility dateUtility;
	
	public UpdateListForm() {
		dateUtility = new DateUtility();
	}
	
	@NotNull(message=RegexpMessage.EMPTY)
	private Integer listId;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listName;
	@NotBlank(message = RegexpMessage.DATE)
	private String listDate;
	@NotBlank(message = RegexpMessage.TIME)
	private String listTime;
	
	@AssertTrue(message = RegexpMessage.DATE)
	public boolean isNotDate() {
		return listDate == null || dateUtility.isDateFormat(listDate);
	}
	
	@AssertTrue(message = RegexpMessage.TIME)
	public boolean isNotTime() {
		return listTime == null || dateUtility.isTimeFormat(listTime);
	}
	
	public Date getListDateToDate() {
		return dateUtility.dateFormatStringToDate(listDate);
	}
	
	public Date getListTimeToDate() {
		return dateUtility.timeFormatStringToDate(listTime);
	}
}
