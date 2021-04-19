package com.example.demo.form.see.ListInSpace;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import lombok.Data;

@Data
public class HeaderSpaceForm {
	
	private Date assignmentDate;
	private String assignmentType;
	
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";
	
	public static HeaderSpaceForm newInstanceByYear(String year) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return new HeaderSpaceForm(format.parse(year),YEAR);
	}
	
	public static HeaderSpaceForm newInstanceByMonth(String month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M");
		return new HeaderSpaceForm(format.parse(month),MONTH);
	}

	public static HeaderSpaceForm newInstanceByDay(String day) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
		return new HeaderSpaceForm(format.parse(day),DAY);
	}
	
	private HeaderSpaceForm(Date date,String typeName) {
		assignmentDate = date;
		assignmentType = typeName;
	}
	
	public String getYrar() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(assignmentDate);
	}
	
	public String getMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M");
		return format.format(assignmentDate);
	}
	
	public String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
		return format.format(assignmentDate);
	}
	
	public String getBackDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(assignmentDate);
		SimpleDateFormat format = new SimpleDateFormat("");
		
		switch(assignmentType) {
		case DAY:
			format = new SimpleDateFormat("yyyy/M/d");
			calendar.set(Calendar.DAY_OF_MONTH, -1);
			break;
		case MONTH:
			format = new SimpleDateFormat("yyyy/M");
			calendar.set(Calendar.MONTH, -1);
			break;
		case YEAR:
			format = new SimpleDateFormat("yyyy");
			calendar.set(Calendar.YEAR, -1);
			break;
		}
		
		return format.format(
				calendar.getTime());
	}
	
	public String getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(assignmentDate);
		SimpleDateFormat format = new SimpleDateFormat("");
		
		switch(assignmentType) {
		case DAY:
			format = new SimpleDateFormat("yyyy/M/d");
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			break;
		case MONTH:
			format = new SimpleDateFormat("yyyy/M");
			calendar.set(Calendar.MONTH, 1);
			break;
		case YEAR:
			format = new SimpleDateFormat("yyyy");
			calendar.set(Calendar.YEAR, 1);
			break;
		}
		
		return format.format(
				calendar.getTime());
	}
}