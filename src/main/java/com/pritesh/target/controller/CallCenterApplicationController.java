package com.pritesh.target.controller;

import com.pritesh.target.model.Call;
import com.pritesh.target.model.CallCenterResponse;
import com.pritesh.target.model.CallSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by pripatha on 11/5/2017.
 */
@RestController
public class CallCenterApplicationController {

    @RequestMapping("/callcenter")
    public CallSummary getCallSummary(@RequestParam (value = "callEntry") String callEntry){
        String [] callValues = callEntry.split(",");
        Call [] calls = new Call[callValues.length];
        for(int i=0;i<callValues.length;i++){
            calls[i] = new Call(callValues[i]);
        }
        CallCenterResponse response = new CallCenterResponse(calls);
        return response.processCalls();
    }
}
