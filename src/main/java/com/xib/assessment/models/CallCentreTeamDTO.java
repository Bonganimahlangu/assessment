package com.xib.assessment.models;

public class CallCentreTeamDTO {

    private long id;
    private String name;
    private boolean validateTeamName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidateTeamName() {
        return validateTeamName;
    }

    public void setValidateTeamName(boolean validateTeamName) {
        this.validateTeamName = validateTeamName;
    }

    @Override
    public String toString() {
        return "CallCentreTeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", validateTeamName=" + validateTeamName +
                '}';
    }
}
