package com.xib.assessment.sterotypes;

import com.xib.assessment.domain.Agent;
import com.xib.assessment.domain.Manager;
import com.xib.assessment.domain.Team;
import com.xib.assessment.dao.AgentRepository;
import com.xib.assessment.dao.ManagerRepository;
import com.xib.assessment.dao.TeamRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
public class LoadTestData {

    private final AgentRepository agentRepository;
    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;

    public LoadTestData(AgentRepository agentRepository, ManagerRepository managerRepository, TeamRepository teamRepository) {
        this.agentRepository = agentRepository;
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    @Transactional
    public void execute() {
        Manager manager1 = createManager("TestFirstName_1", "TestLastName_1", "9211135699087");
        Manager manager2 = createManager("TestFirstName_2", "TestLastName_2", "7605175699082");

        Team team1 = createTeam("Marvel Team",manager1);
        Team team2 = createTeam("DC Team",manager2);

        createAgent("Bruce", "Banner", "1011125190081", team1,manager1);
        createAgent("Tony", "Stark", "6912115191083", team1,manager1);
        createAgent("Peter", "Parker", "7801115190084", team1,manager2);
        createAgent("Bruce", "Wayne", "6511185190085", team2,manager2);
        createAgent("Clark", "Kent", "5905115190086",team2,manager1);
        createAgent("TestFirstName_2", "TestLastName_2", "7605175699082", team2,manager1);

    }

    private Team createTeam(String name, Manager manager) {
        Team t = new Team();
        t.setName(name);
        t.getManagers().add(manager);
        t.setManagers(t.getManagers());
        return teamRepository.save(t);
    }

    private void createAgent(String firstName, String lastName, String idNumber, Team team, Manager manager) {
        Agent a = new Agent();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        a.setTeam(team);
        a.setManager(manager);
        agentRepository.save(a);
    }

    private Manager createManager(String firstName, String lastName, String idNumber) {
        Manager a = new Manager();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        return managerRepository.save(a);
    }
}
