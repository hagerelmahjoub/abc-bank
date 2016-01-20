//package com.abc;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.junit.Ignore;
//
///**
// * this class is a singleton, We could not use this class, I added the @Ignore
// * annotation
// * 
// * @author hager.elmahjoub
// */
//@Ignore
//public class DateProvider {
//
//	/**
//	 * 
//	 */
//	private static volatile DateProvider instance;
//
//	/**
//	 * 
//	 */
//	private DateProvider() {
//
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public static DateProvider getInstance() {
//		if (instance == null) {
//			synchronized (DateProvider.class) {
//				if (instance == null) {
//					instance = new DateProvider();
//				}
//			}
//		}
//		return instance;
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public Date now() {
//		return Calendar.getInstance().getTime();
//	}
//}
