package nxsupert.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A universally accessible thread safe pool of data. Not nice in java but can be useful in dynamic systems designed in other languages (Such as JavaScript).
 * 
 * @author nxsupert
 * @version 1.1
 *
 */
public class DataPool {
	private static Map<String, Object> dataMap;
	
	static {
		dataMap = new HashMap<String, Object>();
	}
	
	public static void put(String location, Object object) {
		synchronized(dataMap) {
			dataMap.put(location, object);
		}
	}
	
	public static Object get(String location) {
		Object data;
		synchronized(dataMap) {
			if(dataMap.containsKey(location)){
				data =  dataMap.get(location);
			} else {
				data = null;
			}
		}
		
		return data;
	}
	
	public static void clear() {
		synchronized(dataMap) {
			dataMap.clear();
		}
	}
	
	
	
}
