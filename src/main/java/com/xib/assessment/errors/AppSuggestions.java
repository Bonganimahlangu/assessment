package com.xib.assessment.errors;

public class AppSuggestions {
    private String message;

    public AppSuggestions(String message) {
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
