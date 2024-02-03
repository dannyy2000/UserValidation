public class ValidationCheck {

    private boolean isValid;
    private String errorMessage;


    public ValidationCheck(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public ValidationCheck(){
        isValid = true;
        errorMessage = "";
    }


    public void sendError(String error){
        isValid = false;
        errorMessage += error + "\n";
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
