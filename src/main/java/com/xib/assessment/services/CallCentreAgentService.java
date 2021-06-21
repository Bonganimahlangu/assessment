package com.xib.assessment.services;


import com.xib.assessment.mappings.AppAssembler;
import com.xib.assessment.models.CallCentreAgentDTO;
import com.xib.assessment.domain.Agent;
import com.xib.assessment.errors.AppError;
import com.xib.assessment.dao.AgentRepository;
import com.xib.assessment.dao.ManagerRepository;
import com.xib.assessment.services.interfaces.CallCentreAgentServiceIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCentreAgentService implements CallCentreAgentServiceIntf {

    private final AgentRepository agentRepository;
    private final ManagerRepository managerRepository;

    public CallCentreAgentService(AgentRepository agentRepository, ManagerRepository managerRepository) {
        this.agentRepository = agentRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Agent> findAllCallCentreAgents() throws AppError {

        List<Agent> agents = agentRepository.findAll();

        if (agents.isEmpty())
            throw new AppError("No CallCentreAgents found!");

        return agents;
    }


    @Override
    public Agent findCallCentreAgent(long id) throws AppError {

        try {
            return agentRepository.findById(id)
                    .orElseThrow(() -> new AppError("Agent not found for this id: " + id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
    }



    @Override
    public Agent saveCallCentreAgent(CallCentreAgentDTO callCentreAgentDto) throws AppError {

        Agent agent = AppAssembler.assembleAgent(callCentreAgentDto);

        try {
            if(agent.getIdNumber() == null)
                throw new AppError("Agent ID Number is mandatory.");

            if(agentRepository.existsByIdNumber(agent.getIdNumber()))
                throw new AppError("Agent exists.");

            if(callCentreAgentDto.getManagerId() == null)
                throw new AppError("Manager is mandatory.");

            if(!managerRepository.existsById(callCentreAgentDto.getManagerId()))
                throw new AppError("Manager does not exist.");

            agent.setManager(managerRepository.findById(callCentreAgentDto.getManagerId()).get());
            agent = agentRepository.save(agent);

        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return agent;
    }
}
