package com.xib.assessment.rest;


import com.xib.assessment.models.CallCentreManagerDTO;
import com.xib.assessment.errors.AppError;
import com.xib.assessment.services.interfaces.CallCentreManagerServiceIntf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager/")
public class CallCentreManagerController {

    private final CallCentreManagerServiceIntf callCentreManagerServiceIntf;

    public CallCentreManagerController(CallCentreManagerServiceIntf callCentreManagerServiceIntf) {
        this.callCentreManagerServiceIntf = callCentreManagerServiceIntf;
    }

    @GetMapping("{managerIdNumber}")
    public ResponseEntity findManager(@PathVariable("managerIdNumber") String managerIdNumber){
        try {
            return new ResponseEntity<>(callCentreManagerServiceIntf.findManager(managerIdNumber), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity createManager(@RequestBody CallCentreManagerDTO callCentreManagerDto){
        try {
            return new ResponseEntity<>( callCentreManagerServiceIntf.saveManager(callCentreManagerDto), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("assign/{managerIdNumber}/team/{teamId}")
    public ResponseEntity assignManagerToTeam(@PathVariable("managerIdNumber") String managerIdNumber,@PathVariable("teamId") long teamId){
        try {
            return new ResponseEntity<>(callCentreManagerServiceIntf.assignManagerToTeam(managerIdNumber, teamId), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("unassign/{managerIdNumber}/team/{teamId}")
    public ResponseEntity unassignManagerToTeam(@PathVariable("managerIdNumber") String managerIdNumber,@PathVariable("teamId") long teamId){
        try {
            return new ResponseEntity<>(callCentreManagerServiceIntf.unassignManagerToTeam(managerIdNumber, teamId), HttpStatus.OK);
        } catch (AppError appError) {
            return new ResponseEntity<>(appError.getBusinessRuleMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
