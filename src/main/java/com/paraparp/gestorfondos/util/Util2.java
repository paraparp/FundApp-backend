package com.paraparp.gestorfondos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util2 {

	public static BigDecimal utilDivideBD(BigDecimal bd1, BigDecimal bd2) {

		BigDecimal result = BigDecimal.ZERO;

		try {
			result = bd1.divide(bd2, 4, RoundingMode.HALF_UP);
		} catch (Exception e) {
			result = BigDecimal.ZERO;
		}

		System.out.println(result);
		return result;
	}

}
