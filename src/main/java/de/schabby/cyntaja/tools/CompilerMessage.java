package de.schabby.cyntaja.tools;

public class CompilerMessage {

    private boolean isError = false;

    private String message;

    public CompilerMessage(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public String getMessage() {
        return message;
    }
}
