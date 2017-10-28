package updateemployeebyid;

public class ResponseMessage {
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
    	String response = " { \"Message\":\"" + this.getMessage() + "\"}"; 
    	return response;
    }
}
