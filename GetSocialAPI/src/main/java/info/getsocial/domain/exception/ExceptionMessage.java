package info.getsocial.domain.exception;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionMessage {
	NOT_FOUND(404, "Entity not found"),
	INVALID_PARAMETERS(418, "Invalid request parameters"),
	INTERNAL_SERVER_ERROR(500, "Internal Server Error");

	private int code;
	private String message;

	private static Map<Integer, ExceptionMessage> map = new HashMap<Integer, ExceptionMessage>();

	static {
		for (ExceptionMessage exception : ExceptionMessage.values()) {
			map.put(exception.getCode(), exception);
		}
	}

	private ExceptionMessage(final int code, final String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public static ExceptionMessage valueOf(int code) {
		return map.get(code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
