package org.javaforever.myareas.utils;
public class StringUtil {
	public static boolean isBlank(Object o) {
		if (o == null || "".equals(o))
			return true;
		else
			return false;
	}

	public static String nullTrim(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str) ? "" : str;
	}
	
	public static String toNullString(Object ob) {
		if (ob == null) return null;
		else return ob.toString();
	}
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
