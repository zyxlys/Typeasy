package me.imomo.typeasy.commons;

public class Excerpt {
	public static String excerpt(String str) {
		String s = null;
		if(str.length() > 500)
			s = str.substring(0,500)+" [...]";
		else
			s = str;
		return s;
	}

}
