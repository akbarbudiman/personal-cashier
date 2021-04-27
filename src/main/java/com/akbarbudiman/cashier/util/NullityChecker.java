package com.akbarbudiman.cashier.util;

import java.util.List;

public class NullityChecker {

	public static <T> boolean isListEmpty(List<T> list) {
		if (list == null) return true;
		if (list.isEmpty()) return true;
		return false;
	}
	
}
