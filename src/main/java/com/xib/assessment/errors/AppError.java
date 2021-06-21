package com.xib.assessment.errors;


public class AppError extends Exception{

    private String errMsg;
    private AppRules appRulesMessage;
    private AppSuggestions appSuggestions;

    public AppRules getBusinessRuleMessage() {
        return appRulesMessage;
    }

    public void setBusinessRuleMessage(AppRules appRulesMessage) {
        this.appRulesMessage = appRulesMessage;
    }

    public AppError(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
        this.appRulesMessage = new AppRules(errMsg);
        this.appSuggestions = new AppSuggestions(errMsg);
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "AppError{" +
                "errorMessage='" + errMsg + '\'' +
                '}';
    }
}
