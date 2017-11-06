package com.pritesh.target.controller;

import com.pritesh.target.model.request.CallCenterRequest;
import com.pritesh.target.model.CallCenterRequestResponse;
import com.pritesh.target.model.response.CallCenterResponse;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pripatha on 11/5/2017.
 */
@RestController
public class CallCenterApplicationController {

    @RequestMapping(value = "/callcenter",method=RequestMethod.POST)
    public CallCenterResponse getCallSummary(@RequestBody CallCenterRequest request){

        CallCenterRequestResponse response = new CallCenterRequestResponse(request);
        return response.processCalls();
    }

}
