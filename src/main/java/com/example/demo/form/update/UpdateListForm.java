package com.example.demo.form.update;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.RegexpMessage;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;
import com.example.demo.utility.DateUtility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateListForm {
	@Autowired
	DateUtility dateUtility;
	
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listId;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String listName;
	@AssertTrue(message = RegexpMessage.DATE)
	private String listDate;
	@AssertTrue(message = RegexpMessage.TIME)
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
