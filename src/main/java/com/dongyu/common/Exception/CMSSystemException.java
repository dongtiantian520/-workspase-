/**
 * 
 */
package com.dongyu.common.Exception;

/**
 * @作者  董宇
 *
 * @时间 :2019年10月11日
 */
public class CMSSystemException extends  RuntimeException{
	private static final long serialVersionUID = 1L;
	public CMSSystemException(){
		super();
	}
	public CMSSystemException(String message){
		super(message);
	}
}
