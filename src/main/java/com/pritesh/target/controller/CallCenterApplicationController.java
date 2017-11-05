package com.pritesh.target.controller;

import com.pritesh.target.model.Call;
import com.pritesh.target.model.CallCenterResponse;
import com.pritesh.target.model.CallSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by pripatha on 11/5/2017.
 */
@RestController
public class CallCenterApplicationController {

    @RequestMapping(value = "/callcenter",method=RequestMethod.POST)
    public CallSummary getCallSummary(@RequestBody Map.Entry<String,String> callEntry){
        String [] callValues = callEntry.getValue().split(",");
        Call [] calls = new Call[callValues.length];
        for(int i=0;i<callValues.length;i++){
            calls[i] = new Call(callValues[i]);
        }
        CallCenterResponse response = new CallCenterResponse(calls);
        return response.processCalls();
    }

}
