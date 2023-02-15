package org.javaforever.myareas.utils;

public class BooleanUtil {

	public static Boolean parseBoolean(String value){
		if ("true".equalsIgnoreCase(value)) return true;
		else if ("false".equalsIgnoreCase(value)) return false;
		else if ("Y".equalsIgnoreCase(value)) return true;
		else if ("N".equalsIgnoreCase(value)) return false;
		else if ("T".equalsIgnoreCase(value)) return true;
		else if ("F".equalsIgnoreCase(value)) return false;
		else if ("yes".equalsIgnoreCase(value)) return true;
		else if ("no".equalsIgnoreCase(value)) return false;
		else if ("1".equalsIgnoreCase(value)) return true;
		else if ("0".equalsIgnoreCase(value)) return false;
		else return null;
	}

	public static Integer parseBooleanInt(String value){
		Boolean b = parseBoolean(value);
		if (b==null) return null;
		else if (b==true) return 1;
		else return 0;
	}
}
