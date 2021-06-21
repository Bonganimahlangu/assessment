package com.xib.assessment.services.interfaces;

import com.xib.assessment.models.CallCentreTeamDTO;
import com.xib.assessment.domain.*;
import com.xib.assessment.domain.Team;
import com.xib.assessment.errors.AppError;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CallCentreTeamServiceIntf {

    /**
     * Finds All Teams on the database
     *
     * @return a list of teams
     * @throws AppError if no team found.
     */
    List<Team> findAllTeams() throws AppError;

    /**
     * Assign an agent to an existing team.
     * @param callCentreTeamDto team details
     * @param agentId agent id
     * @return agent and team associated to
     * @throws AppError
     */
    Agent assignAgentToTeam(CallCentreTeamDTO callCentreTeamDto, long agentId) throws AppError;

    /**
     * Finds a team by Id
     * @param id of the team
     * @return specified team
     * @throws AppError
     */
    Team findTeam(@NotNull long id) throws AppError;

    /**
     * Create a new Team on the database
     * @param callCentreTeamDto team to be saved on the db
     * @return saved agent
     * @throws AppError : if team exists or mandatory fields are empty
     */
    Team saveTeam(CallCentreTeamDTO callCentreTeamDto) throws AppError;

    /**
     * Find team with no agents and managers
     * @return saved agent
     * @throws AppError : if team exists or mandatory fields are empty
     */
    List<Team> findTeamsWithNoManagersAndAgents() throws AppError;
}
