package ua.skillsup.application.market.api.model;


public class ResponseMessage {

    private String status;
    private String message;

    public static ResponseMessage successMessage(String message) {
        return new ResponseMessage("Success", message);
    }

    public static ResponseMessage errorMessage(String message) {
        return new ResponseMessage("Error", message);
    }

    private ResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
