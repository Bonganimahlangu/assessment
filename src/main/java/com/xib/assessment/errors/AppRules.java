package com.xib.assessment.errors;

public class AppRules {
    private String message;

    public AppRules(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AppRules{" +
                "message='" + message + '\'' +
                '}';
    }
}
