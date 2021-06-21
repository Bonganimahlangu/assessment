package com.xib.assessment.services;


import com.xib.assessment.models.CallCentreTeamDTO;
import com.xib.assessment.errors.AppError;
import com.xib.assessment.mappings.AppAssembler;
import com.xib.assessment.domain.*;
import com.xib.assessment.dao.AgentRepository;
import com.xib.assessment.dao.TeamRepository;
import com.xib.assessment.services.interfaces.CallCentreTeamServiceIntf;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Service
public class CallCentreTeamService implements CallCentreTeamServiceIntf {

    private final AgentRepository agentRepository;
    private final TeamRepository teamRepository;

    public CallCentreTeamService(AgentRepository agentRepository, TeamRepository teamRepository) {
        this.agentRepository = agentRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAllTeams() throws AppError {
        List<Team> teams = teamRepository.findAll();

        if (teams.size() == 0)
            throw new AppError("No Teams found!");

        return teams;
    }


    @Override
    public Agent assignAgentToTeam(CallCentreTeamDTO callCentreTeamDto, long agentId) throws AppError {

        Agent agent;
        Team team;

        try {
            if(!agentRepository.existsById(agentId))
                throw new AppError("Agent does not exists.");

            if(!teamRepository.existsById(callCentreTeamDto.getId()))
                throw new AppError("Team does not exists.");

            agent = agentRepository.getOne(agentId);
            team = teamRepository.findById(callCentreTeamDto.getId()).get();

            boolean agentManagerManagesOtherTeams = false;

            // this stops an agent from being assigned to a different manager
            if(agent.getManager() != null){
                // Compare managers
                if(team.getManagers() != null) {
                    Set<Manager> teamManagerSet = team.getManagers();

                    agentManagerManagesOtherTeams = teamManagerSet.contains(agent.getManager());

                    if(!agentManagerManagesOtherTeams)
                        throw new AppError(
                                "An agent can only be assigned to a team that is managed by the same manager she/he reports to.");
                } else {
                    throw new AppError(
                            "No Managers associated with team. An agent can only be assigned to a team that is managed by the same manager she/he reports to.");
                }
            }

            agent.setTeam(team);

            if(callCentreTeamDto.isValidateTeamName()){
                if(!callCentreTeamDto.getName().trim().equalsIgnoreCase(team.getName().trim()))
                    throw new AppError("Provided Team name does not match the existing team name.");
            }
            agent = agentRepository.save(agent);


        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return agent;
    }


    @Override
    public Team findTeam(@NotNull long id) throws AppError {
        try {

            return teamRepository.findById(id)
                    .orElseThrow(() -> new AppError("Team not found for id: " + id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
    }


    @Override
    public Team saveTeam(CallCentreTeamDTO callCentreTeamDto) throws AppError {

        Team team = AppAssembler.assembleTeam(callCentreTeamDto);

        try {
            if(teamRepository.existsByNameIgnoreCase(callCentreTeamDto.getName()))
                throw new AppError("Team Name exists.");

            team = teamRepository.save(team);

        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return team;
    }



    @Override
    public List<Team> findTeamsWithNoManagersAndAgents() throws AppError {

        try {
            return teamRepository.findAllByAgentsNullAndManagersNull();
        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
    }
}
