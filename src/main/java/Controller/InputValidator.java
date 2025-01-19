package Controller;

import java.util.regex.*;

public class InputValidator {
    private static Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z]+[0-9]+@[a-z]+.com$");
    private static Pattern NAME_PATTERN =
            Pattern.compile("^[A-Z](.+){2,50}");
    private static Pattern ADDRESS_PATTERN =
            Pattern.compile("^[A-Za-z0-9,\\.\\- ]{5,100}$");
    private static Pattern PHONE_PATTERN =
            Pattern.compile("^\\+[0-9]{1,3}[ ][0-9].+");
    private static Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,50}$");

    public static boolean isNameValid(String name){
        return !name.isEmpty() && NAME_PATTERN.matcher(name).matches();
    }
    public static boolean isPasswordValid(String pass){
        return !pass.isEmpty() && PASSWORD_PATTERN.matcher(pass).matches();
    }
    public static  boolean isEmailValid(String Email){
        return !Email.isEmpty() && EMAIL_PATTERN.matcher(Email).matches();
    }
    public static boolean isAddressValid(String Address){
        return !Address.isEmpty() && ADDRESS_PATTERN.matcher(Address).matches();
    }
    public static boolean isPhoneValid(String Phone){
        return Phone.length() == 14 && PHONE_PATTERN.matcher(Phone).matches();
    }
    public static boolean doesPasswordsMatch(String pass1,String pass2){
        return !pass1.isEmpty() && pass1.equals(pass2);
    }
    public static boolean noSqli(String Input){
        String lower = Input.toLowerCase();
        return !(lower.contains("'") || lower.contains("--") || lower.contains(";") || lower.contains("/*"));
    }


}
