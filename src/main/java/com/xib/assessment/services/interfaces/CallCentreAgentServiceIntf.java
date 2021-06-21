package com.xib.assessment.services.interfaces;

import com.xib.assessment.models.CallCentreAgentDTO;
import com.xib.assessment.entity.Agent;
import com.xib.assessment.errors.AppError;

import java.util.List;

public interface CallCentreAgentServiceIntf {

    /**
     * Finds All CallCentreAgents on the database
     * Applies pagination
     * @return a list of CallCentreAgents
     * @throws AppError if no CallCentreAgents found.
     * @param pageNo : specifies the page number
     * @param pageSize : specifies the number of items(size) on the page
     */
    List<Agent> findAllCallCentreAgentsWithPaging(Integer pageNo, Integer pageSize) throws AppError;

    /**
     * Finds All CallCentreAgents on the database
     * @return a list of CallCentreAgents
     * @throws AppError if no CallCentreAgents found.
     */
    List<Agent> findAllCallCentreAgents() throws AppError;

    /**
     * Finds the Agent
     *
     * @param id: agent pk: identification
     * @return :
     * @throws AppError
     */
    Agent findCallCentreAgent(long id) throws AppError;

    /**
     * Create a new Agent on the database
     * @param callCentreAgentDto
     * @return saved agent
     * @throws AppError : if agent exists or mandatory fields are empty
     */
    Agent saveCallCentreAgent(CallCentreAgentDTO callCentreAgentDto) throws AppError;
}
