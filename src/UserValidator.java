public class UserValidator {

    public ValidationCheck validateUser(User user){

        ValidationCheck validationCheck = new ValidationCheck();

        if(user.getUserName().isEmpty() || user.getUserName().length() < 4){
            validationCheck.sendError("Username must not be empty and minimum of 4 characters");
        }

        if(user.getEmail().isEmpty() || !user.getEmail().matches("^[\\w.-]+@([\\w-]+\\.)+[\\w]{2,4}$")){
            validationCheck.sendError("Email must not be empty, enter a valid email address");
        }

        if(user.getPassword().isEmpty() || !user.getPassword().matches("^(?=.*[A-Z])(?=.*[@#$%^&*])(?=.*[0-9]).{8,}$")){
            validationCheck.sendError("Password must not be empty, a valid password is required ");
        }

        if(user.getDateOfBirth().isEmpty() || !user.getDateOfBirth().matches("^\\d{4}-\\d{2}-\\d{2}$")){
            validationCheck.sendError("Date of birth must not be empty, should be 16 years or greater");
        }

        return validationCheck;

      }
}
