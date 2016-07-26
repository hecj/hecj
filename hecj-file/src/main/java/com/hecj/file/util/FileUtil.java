package com.hecj.file.util;

public class FileUtil {

	public static String getExt(String filename) {
		String[] s = filename.split("\\.");
		String ext = "";
		if (s.length > 1) {
			ext = s[s.length - 1];
		}
		return ext;
	}
}
