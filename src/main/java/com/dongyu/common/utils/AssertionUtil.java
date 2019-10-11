/**
 * 
 */
package com.dongyu.common.utils;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.dongyu.common.Exception.CMSSystemException;

/**
 * @作者  董宇
 *	断言工具类
 * @时间 :2019年10月11日
 */

public class AssertionUtil {
	
	private AssertionUtil(){
		
	}
	
	public static void assertFalse(Boolean target,String message){
		if (target) {
			throw new CMSSystemException(message);
		}
	}
	public static void main(String[] args) {
		boolean flag = true;
		AssertionUtil.assertFalse(flag, "123");
	}
	
	
	

}
