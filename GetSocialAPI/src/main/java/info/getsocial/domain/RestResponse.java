package info.getsocial.domain;

public class RestResponse {

	private int status;
	private String message;
	private Object data;

	public RestResponse() {}
	
	public RestResponse(int status, Object data) {
		this.setStatus(status);
		this.setData(data);
	}

	// getters and setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}