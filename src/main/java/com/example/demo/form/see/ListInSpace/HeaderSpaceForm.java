package com.example.demo.form.see.ListInSpace;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import lombok.Data;

@Data
public class HeaderSpaceForm {
	
	private Date assignmentDate;
	private final String assignmentType;
	private String spaceName;
	
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";
	
	public static HeaderSpaceForm newInstanceByYear(String year,String spaceNameParam) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return new HeaderSpaceForm(format.parse(year),YEAR,spaceNameParam);
	}
	
	public static HeaderSpaceForm newInstanceByMonth(String month,String spaceNameParam) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M");
		return new HeaderSpaceForm(format.parse(month),MONTH,spaceNameParam);
	}

	public static HeaderSpaceForm newInstanceByDay(String day,String spaceNameParam) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
		return new HeaderSpaceForm(format.parse(day),DAY,spaceNameParam);
	}
	
	private HeaderSpaceForm(Date date,String typeName,String spaceNameParam) {
		spaceName = spaceNameParam;
		assignmentDate = date;
		assignmentType = typeName;
	}
	
	public String getYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(assignmentDate);
	}
	
	public String getMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M");
		return format.format(assignmentDate);
	}
	
	public String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
		return format.format(assignmentDate);
	}
	
	public Integer getYearNum() {
		Calendar calrendar = Calendar.getInstance();
		calrendar.setTime(assignmentDate);
		return calrendar.get(Calendar.YEAR);
	}
	
	public Integer getMonthNum() {
		Calendar calrendar = Calendar.getInstance();
		calrendar.setTime(assignmentDate);
		return calrendar.get(Calendar.MONTH) + 1;
	}
	
	public Integer getDayNum() {
		Calendar calrendar = Calendar.getInstance();
		calrendar.setTime(assignmentDate);
		return calrendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public String getBackDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(assignmentDate);
		SimpleDateFormat format = new SimpleDateFormat("");
		
		switch(assignmentType) {
		case DAY:
			format = new SimpleDateFormat("yyyy-M-d");
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			break;
		case MONTH:
			format = new SimpleDateFormat("yyyy-M");
			calendar.add(Calendar.MONTH, -1);
			break;
		case YEAR:
			format = new SimpleDateFormat("yyyy");
			calendar.add(Calendar.YEAR, -1);
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
			format = new SimpleDateFormat("yyyy-M-d");
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case MONTH:
			format = new SimpleDateFormat("yyyy-M");
			calendar.add(Calendar.MONTH, 1);
			break;
		case YEAR:
			format = new SimpleDateFormat("yyyy");
			calendar.add(Calendar.YEAR, 1);
			break;
		}
		
		return format.format(
				calendar.getTime());
	}
	
	public String getAssignmentDateString() {
		switch(assignmentType) {
		case DAY:
			return getDay().replace('-', '/');
		case MONTH:
			return getMonth().replace('-', '/');
		case YEAR:
			return getYear();
		default:
			return null;
		}
	}
}