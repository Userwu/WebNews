package cn.edu.nsu.tools;

public class ChangeCode {
	public static String unicodeToUtf8(String unicode){
		String str = "";
		try{
			StringBuilder sb = new StringBuilder();
			unicode = unicode.substring(1,unicode.length());
			String[] words = unicode.split("u");
			for(String w : words){
				char s = (char)(Integer.parseInt(w, 16));
				sb.append(s);
			}
			str = sb.toString();
		}catch(Exception e){
			str = unicode;
		}
		return str;
	}
	public static String utf8ToUnicode(String tt){
		StringBuilder unicode = new StringBuilder();
		for(int i = 0 ;i < tt.length(); i++)
			unicode.append("u" + Integer.toHexString(tt.charAt(i)));
		return unicode.toString();
	}
}
