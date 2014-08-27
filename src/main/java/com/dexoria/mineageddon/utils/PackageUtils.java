package com.dexoria.mineageddon.utils;

public class PackageUtils {
	public boolean classExists(String classFullName) {
		try {
		    Class.forName(classFullName);
		    return true;
		} catch(Exception e) {
		    return false;
		}
	}
}
