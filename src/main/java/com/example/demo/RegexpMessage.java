package com.example.demo;

public class RegexpMessage {
	public static final String INTEGER = "整数で入力してください";
	public static final String AUTHORITY = "権限は「MASTER」か「USER」でお願いします";
	public static final String EMPTY = "何かしら入力してください（空っぽはだめです）";
	public static final String RATE = "率の指定は、0以上1以下でお願いします";
	public static final String ID_OR_NAME = "番号（整数）か、名前等を入力してください";
	public static final String ID_OR_ID_AND_NAME = "番号（整数）で指定するかデータリストから選択してください";
	public static final String DATE = "日付を、「2020/9/8」のように入力してください";
	public static final String TIME = "時間を、「19:50」のように入力してください";
	public static final String SPACE = "選択肢から選んでください";
	
	private RegexpMessage() {}
}
