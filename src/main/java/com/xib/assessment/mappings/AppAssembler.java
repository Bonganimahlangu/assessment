package com.xib.assessment.mappings;

import com.xib.assessment.models.CallCentreAgentDTO;
import com.xib.assessment.models.CallCentreManagerDTO;
import com.xib.assessment.models.CallCentreTeamDTO;
import com.xib.assessment.domain.*;

public class AppAssembler {

    public static Agent assembleAgent(CallCentreAgentDTO callCentreAgentDto) {

        Agent agent = new Agent();
        agent.setFirstName(callCentreAgentDto.getFirstName());
        agent.setLastName(callCentreAgentDto.getLastName());
        agent.setIdNumber(callCentreAgentDto.getIdNumber());

        return agent;
    }

    public static Team assembleTeam(CallCentreTeamDTO callCentreTeamDto) {

        Team team = new Team();
        team.setName(callCentreTeamDto.getName());
        return team;
    }

    public static Manager assembleManager(CallCentreManagerDTO callCentreManagerDto) {

        Manager manager = new Manager();
        manager.setFirstName(callCentreManagerDto.getFirstName());
        manager.setLastName(callCentreManagerDto.getLastName());
        manager.setIdNumber(callCentreManagerDto.getIdNumber());
        return manager;
    }
}
