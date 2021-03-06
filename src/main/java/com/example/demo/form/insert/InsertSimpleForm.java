package com.example.demo.form.insert;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;
import com.example.demo.utility.DateUtility;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InsertSimpleForm {
	DateUtility dateUtility;
	
	public InsertSimpleForm() {
		dateUtility = new DateUtility();
	}
	
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listName;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listDate;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listTime;
	
	@AssertTrue(message = RegexpMessage.DATE)
	public boolean isNotDate() {
		return listDate == null || 
				dateUtility.isDateFormat(listDate);
	}
	
	@AssertTrue(message = RegexpMessage.TIME)
	public boolean isNotTime() {
		return listTime == null ||
				dateUtility.isTimeFormat(listTime);
	}
	
	public Date getListDateToDate() {
		return dateUtility.dateFormatStringToDate(listDate);
	}
	
	public Date getListTimeToDate() {
		return dateUtility.timeFormatStringToDate(listTime);
	}
}
