package com.example.demo.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public Date dateFormatStringToDate(String date) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
			format.setLenient(false);
			return format.parse(date);
			
		}catch(Exception ex){
			return null;
		}
	}

	public Date timeFormatStringToDate(String time) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("H:m");
			format.setLenient(false);
			return format.parse(time);
			
		}catch(Exception ex){
			return null;
		}
	}
	
	public boolean isDateFormat(String date) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
			format.setLenient(false);
			format.parse(date);
		 
		    return true;		 
		}catch(Exception ex){
			return false;
		}
	}

	public boolean isTimeFormat(String time) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("H:m");
			format.setLenient(false);
			format.parse(time);
		 
		    return true;		 
		}catch(Exception ex){
			return false;
		}
	}
	
}