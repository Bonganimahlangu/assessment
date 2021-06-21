package com.xib.assessment.controllers;

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

    @GetMapping("all/withPaging")
    public ResponseEntity findAllAgentsWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.findAllCallCentreAgentsWithPaging(pageNo, pageSize), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findAgent(@PathVariable("id") @NotNull long id){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.findCallCentreAgent(id), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity createAgent(@RequestBody CallCentreAgentDTO agent ){
        try {
            return new ResponseEntity<>( callCentreAgentServiceIntf.saveCallCentreAgent(agent), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
