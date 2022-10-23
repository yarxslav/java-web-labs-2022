package ua.kpi.ispservice.view;

public enum ErrorMessages {

    USER_NOT_EXISTS_ERROR("User with such username doesn't exist!"),
    PASSWORD_NOT_CORRECT("Password not correct! Try again!"),
    USER_ALREADY_EXISTS("User with such username already exists. Try again!");

    public final String value;

    private ErrorMessages(String value) {
        this.value = value;
    }
}
