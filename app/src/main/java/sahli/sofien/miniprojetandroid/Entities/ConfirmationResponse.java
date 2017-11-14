package sahli.sofien.miniprojetandroid.Entities;

/**
 * Created by sofien on 02/11/2017.
 */

public class ConfirmationResponse {
    boolean success;
    String message ;

    public ConfirmationResponse() {
    }

    public ConfirmationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
