package gg.stephen.pt.game;

public class Game {

    private String password;

    private GameState state = GameState.SET_PASSWORD;
    private int toEnter = 5;

    public String[] next(String input) {
        switch (state) {
            case SET_PASSWORD:
                if (input.length() < 5) return new String[]{"<html><font color='red'>Invalid input.</font></html>", "Password length must be more than 5."};
                password = input;
                state = GameState.ENTER_NUMBER;
                return new String[]{"<html><font color='green'>Password set!</font></html>", "Enter first " + toEnter + " characters of password:"};
            case ENTER_NUMBER:
                if (!input.equals(password.substring(0, toEnter))) return new String[]{"<html><font color='red'>Incorrect.</font></html>", "Enter first " + toEnter + " characters of password:"};
                if (toEnter == password.length() - 1) {
                    state = GameState.ENTER_FULL;
                    return new String[]{"<html><font color='green'>Correct!</font></html>", "Enter the full password:"};
                }
                toEnter++;
                return new String[]{"<html><font color='green'>Correct!</font></html>", "Enter first " + toEnter + " characters of password:"};
            case ENTER_FULL:
                if (!input.equals(password)) return new String[]{"<html><font color='red'>Incorrect.</font></html>", "Enter the full password:"};
                state = GameState.SET_PASSWORD;
                toEnter = 5;
                return new String[]{"<html><font color='green'>Completed successfully!</font></html>", "Enter password again to continue training."};
        }
        return null;
    }

}