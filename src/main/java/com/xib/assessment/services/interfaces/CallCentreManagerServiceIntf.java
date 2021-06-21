package com.xib.assessment.services.interfaces;

import com.xib.assessment.models.CallCentreManagerDTO;
import com.xib.assessment.entity.Manager;
import com.xib.assessment.entity.Team;
import com.xib.assessment.errors.AppError;

import javax.validation.constraints.NotNull;

public interface CallCentreManagerServiceIntf {

    /**
     * Create a new Manager on the database
     * @param callCentreManagerDto
     * @return saved manager
     * @throws AppError : if team exists or mandatory fields are empty
     */
    Manager saveManager(CallCentreManagerDTO callCentreManagerDto) throws AppError;

    /**
     * Assigns a manager to a team.
     * @param managerIdNumber
     * @param teamId
     * @return
     * @throws AppError
     */
    Team assignManagerToTeam(String managerIdNumber, long teamId) throws AppError;

    /**
     * Unassigns a manager from a team
     * @param managerIdNumber
     * @param teamId
     * @return
     * @throws AppError
     */
    Team unassignManagerToTeam(String managerIdNumber, long teamId) throws AppError;

    /**
     * Finds a manager in the database
     * @param managerIdNumber: Id Document Number
     * @return manager
     * @throws AppError
     */
    Manager findManager(@NotNull String managerIdNumber) throws AppError;
}
