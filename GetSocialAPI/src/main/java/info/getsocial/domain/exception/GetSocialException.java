package info.getsocial.domain.exception;

public class GetSocialException extends Exception {
	private static final long serialVersionUID = 1L;
	private int code;
	private ExceptionMessage exceptionMessage;
	
	public GetSocialException( int code ) {
		this.setCode(code);
		this.setMessage(ExceptionMessage.valueOf(code));
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return exceptionMessage.getMessage();
	}

	public void setMessage(ExceptionMessage message) {
		this.exceptionMessage = message;
	}
}
