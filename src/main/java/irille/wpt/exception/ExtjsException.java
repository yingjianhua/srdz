package irille.wpt.exception;

import java.text.MessageFormat;

public class ExtjsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExtjsException(String msg) {
		super(msg);
	}
	public ExtjsException(String msg, Object ... params) {
		super(new MessageFormat(msg).format(params));
	}
}
