package com.example.demo;

public class RegexpPattern {
	public static final String INTEGER = "^[0-9]+$";
	public static final String BASE_INTEGER = "[0-9]+";
	public static final String AUTHORITY = "^(" + Authority.MASTER + ")|(" + Authority.USER + ")$";
	public static final String SPACE = "^(" + Space.PUBLIC + ")|(" + Space.PROTECTED + ")|(" + Space.PRIVATE + ")$";
	public static final String EMPTY = "^$";
	public static final String RATE = "^[0-1]|(0\\.[0-9]+)$";
	public static final String ID_OR_NAME = "^(([0-9]+)|(.*))?$";
	public static final String ID_OR_ID_AND_NAME = "^([0-9]+)|([0-9]+\\:.+)$";
	public static final String ID_AND_NAME = "^([0-9]+)\\:.+$";
	public static final String DATE = "^([1-2][0-9]{3})/(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])$";
	public static final String BASE_DATE = "([1-2][0-9]{3})/(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])";
	public static final String RANGE = "^(.*)-(.*)$";
	public static final String RANGE_DATE = "^((" + BASE_DATE + ")-(" + BASE_DATE + "))|(" + BASE_DATE + ")?$";
	public static final String RANGE_INTEGER = "^((" + BASE_INTEGER + ")-(" + BASE_INTEGER + "))|(" + BASE_INTEGER + ")?$";
	
	private RegexpPattern() {}
}
