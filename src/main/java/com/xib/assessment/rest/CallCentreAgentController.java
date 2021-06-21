package com.xib.assessment.rest;

import com.xib.assessment.models.CallCentreAgentDTO;
import com.xib.assessment.errors.AppError;
import com.xib.assessment.services.interfaces.CallCentreAgentServiceIntf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("agent/")
public class CallCentreAgentController {

    private final CallCentreAgentServiceIntf callCentreAgentServiceIntf;

    public CallCentreAgentController(CallCentreAgentServiceIntf callCentreAgentServiceIntf) {
        this.callCentreAgentServiceIntf = callCentreAgentServiceIntf;
    }

    @GetMapping("")
    public ResponseEntity findAllAgents(){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.findAllCallCentreAgents(), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "{id}")
    public ResponseEntity getAgent(@PathVariable("id") @NotNull long id){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.findCallCentreAgent(id), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity saveAgent(@RequestBody CallCentreAgentDTO agent ){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.saveCallCentreAgent(agent), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
