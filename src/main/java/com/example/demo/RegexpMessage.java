package com.example.demo;

public class RegexpMessage {
	public static final String INTEGER = "整数で入力してください";
	public static final String AUTHORITY = "権限は「MASTER」か「USER」でお願いします";
	public static final String EMPTY = "何かしら入力してください（空っぽはだめです）";
	public static final String RATE = "率の指定は、0以上1以下でお願いします";
	public static final String ID_OR_NAME = "番号（整数）か、名前等を入力してください";
	public static final String ID_OR_ID_AND_NAME = "番号（整数）で指定するかデータリストから選択してください";
	public static final String DATE = "日付を、「2020-9-8」のように入力してください";
	public static final String RANGE_DATE = "日付を、「2020-9-8」のように入力し、「日付A」、「日付A-日付B」のように入力してください";
	public static final String RANGE_INTEGER = "数字を「12」、「12-20」のように入力してください";
	
	private RegexpMessage() {}
}
