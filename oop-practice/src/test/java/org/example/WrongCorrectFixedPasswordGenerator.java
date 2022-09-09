package org.example;

public class WrongCorrectFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "ab"; //2글자
    }
}
