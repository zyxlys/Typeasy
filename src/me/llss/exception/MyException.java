package me.llss.exception;

/**
 * 自定义异常
 * 
 * @author Acris
 * @version 2.0
 */
public class MyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message; // 定义String类型变量

	public MyException(String ErrorMessagr) { // 父类方法
		message = ErrorMessagr;
	}

	public String getMessage() { // 覆盖getMessage()方法
		return message;
	}
}
