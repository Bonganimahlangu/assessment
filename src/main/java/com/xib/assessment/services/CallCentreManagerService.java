package com.xib.assessment.services;


import com.xib.assessment.models.CallCentreManagerDTO;
import com.xib.assessment.errors.AppError;
import com.xib.assessment.mappings.AppAssembler;
import com.xib.assessment.domain.Manager;
import com.xib.assessment.domain.Team;
import com.xib.assessment.dao.ManagerRepository;
import com.xib.assessment.dao.TeamRepository;
import com.xib.assessment.services.interfaces.CallCentreManagerServiceIntf;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Service
public class CallCentreManagerService implements CallCentreManagerServiceIntf {

    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;

    public CallCentreManagerService(ManagerRepository managerRepository, TeamRepository teamRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public Manager saveManager(CallCentreManagerDTO callCentreManagerDto) throws AppError {

        Manager manager = AppAssembler.assembleManager(callCentreManagerDto);

        try {
            if(managerRepository.existsByIdNumberIgnoreCase(callCentreManagerDto.getIdNumber()))
                throw new AppError("Manager Id Number exists.");

            manager = managerRepository.save(manager);

        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return manager;
    }


    @Override
    public Team assignManagerToTeam(String managerIdNumber, long teamId) throws AppError {

        Manager manager;
        Team team;

        try {
            if(!managerRepository.existsByIdNumberIgnoreCase(managerIdNumber))
                throw new AppError("Manager does not exists.");

            if(!teamRepository.existsById(teamId))
                throw new AppError("Team does not exists.");

            team = teamRepository.getOne(teamId);

            if(team.getManagers() != null){
                for (Manager man :team.getManagers()) {
                    if(man.getIdNumber().equalsIgnoreCase(managerIdNumber))
                        throw new AppError("Manager already exists. Please use remove or update manager function.");
                }
            }

            manager = managerRepository.findByIdNumberIgnoreCase(managerIdNumber);

            Set<Manager> managerSet = team.getManagers();
            managerSet.add(manager);

            team.setManagers(managerSet);
            team = teamRepository.save(team);

        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return team;
    }



    @Override
    public Team unassignManagerToTeam(String managerIdNumber, long teamId) throws AppError {

        Manager manager;
        Team team;
        Manager foundManagerDo= null;
        boolean managerFound = false;

        try {
            if(!managerRepository.existsByIdNumberIgnoreCase(managerIdNumber))
                throw new AppError("Manager does not exists.");

            if(!teamRepository.existsById(teamId))
                throw new AppError("Team does not exists.");

            team = teamRepository.getOne(teamId);
            manager = managerRepository.findByIdNumberIgnoreCase(managerIdNumber);

            if(team.getManagers() == null || team.getManagers().size() ==0)
                throw new AppError("Team does not have manager. Please assign manager.");


            if(team.getManagers() != null){
                for (Manager man :team.getManagers()) {
                    if(man.getIdNumber().equalsIgnoreCase(managerIdNumber)) {
                        managerFound = true;
                        foundManagerDo = man;
                    }
                }
            }

            if(!managerFound){
                throw new AppError("Provided Manager ID does not match with assigned Manager IDs. " +
                        "Note: To remove a current manager from the Team, note that the ID Number you provide has to match with" +
                        "one of the saved Manager ID Numbers.");

            } else {
                // Manager Unassigned from team
                Set<Manager> managerSet = team.getManagers();
                if(managerSet.remove(foundManagerDo)){
                    team.setManagers(managerSet);
                    team = teamRepository.save(team);
                } else {
                    throw new AppError("Manager could not be removed from team.");
                }
            }
        }catch (Exception a){
            a.printStackTrace();
            throw new AppError(a.getMessage());
        }
        return team;
    }


    @Override
    public Manager findManager(@NotNull String managerIdNumber) throws AppError {
        try {
            Manager manager = managerRepository.findByIdNumberIgnoreCase(managerIdNumber);

            if(manager == null)
                   throw new AppError("Manager not found for this id number: " + managerIdNumber);

            return manager;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
    }
}
