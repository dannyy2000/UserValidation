import java.util.Scanner;

public class UserAppMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the username:  ");
        String username = input.nextLine();

        System.out.print("Enter the email: ");
        String email = input.nextLine();

        System.out.print("Enter the password:  ");
        String password = input.nextLine();

        System.out.print("Enter the date of birth:  ");
        String dob = input.nextLine();


        User user = new User(username,email,password,dob);

        UserValidator userValidator = new UserValidator();

        ValidationCheck validationCheck = userValidator.validateUser(user);

        if(validationCheck.isValid()){
            TokenUtils tokenUtils = new TokenUtils();
            String token = tokenUtils.generateToken(user.getUserName());
            String verify = tokenUtils.verifyToken(token);
            System.out.println("This is the token: "+token);
        }else {
            System.out.println(validationCheck.getErrorMessage());
        }

    }
}
