package com.xib.assessment.controllers;


import com.xib.assessment.errors.AppError;
import com.xib.assessment.models.CallCentreTeamDTO;
import com.xib.assessment.services.interfaces.CallCentreTeamServiceIntf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("team/")
public class CallCentreTeamController {

    private final CallCentreTeamServiceIntf callCentreTeamServiceIntf;

    public CallCentreTeamController(CallCentreTeamServiceIntf callCentreTeamServiceIntf) {
        this.callCentreTeamServiceIntf = callCentreTeamServiceIntf;
    }

    @GetMapping
    public ResponseEntity findAllTeams(){
        try {
            return new ResponseEntity<>(callCentreTeamServiceIntf.findAllTeams(), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("empty/managers/agents")
    public ResponseEntity findTeamsWithNoManagersAndAgents(){
        try {
            return new ResponseEntity<>(callCentreTeamServiceIntf.findTeamsWithNoManagersAndAgents(), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findTeam(@PathVariable("id") long id){
        try {
            return new ResponseEntity<>(callCentreTeamServiceIntf.findTeam(id), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}/agent")
    public ResponseEntity assignAgentToTeam(@PathVariable("id") long id, @RequestBody CallCentreTeamDTO callCentreTeamDto){
        try {
            return new ResponseEntity<>(callCentreTeamServiceIntf.assignAgentToTeam(callCentreTeamDto, id), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity createTeam(@RequestBody CallCentreTeamDTO callCentreTeamDto){
        try {
            return new ResponseEntity<>( callCentreTeamServiceIntf.saveTeam(callCentreTeamDto), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
