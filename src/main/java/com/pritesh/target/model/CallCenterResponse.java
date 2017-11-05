package com.pritesh.target.model;

import com.pritesh.target.service.CallService;
import com.pritesh.target.service.Employee;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by pripatha on 11/5/2017.
 */
public class CallCenterResponse {
    Queue<Call> juniorQueue;
    Queue<Call> seniorQueue;
    Queue<Call> managerQueue;
    Queue<Call> unresolvedQueue;
    //JuniorExecutive
    Employee juniorExecutive[];
    //SeniorExecutive
    Employee seniorExecutive[];
    //Manager
    Employee manager[];
    private Call[] calls;
    private int thresholdCalls;
    public CallCenterResponse(Call[] calls){
        this.calls = calls;
        juniorQueue = new LinkedList<Call>();
        seniorQueue = new LinkedList<Call>();
        managerQueue = new LinkedList<Call>();
        unresolvedQueue = new LinkedList<Call>();
        juniorExecutive = new Employee[ConfigConstants.JUNIOR_EXECUTIVES];
        seniorExecutive = new Employee[ConfigConstants.SENIOR_EXECUTIVES];
        manager = new Employee[ConfigConstants.MANAGERS];
        thresholdCalls = calls.length/ConfigConstants.JUNIOR_EXECUTIVES + ConfigConstants.SENIOR_EXECUTIVES;
    }
    public CallSummary processCalls(){
        for(Call call:calls){
            juniorQueue.add(call);
        }

        CallService service = new CallService(juniorQueue,seniorQueue,managerQueue,unresolvedQueue,juniorExecutive,seniorExecutive,manager,thresholdCalls);
        boolean allCallsServiced = service.serviceCalls();
        CallSummary callSummary = null;
        if(allCallsServiced){
             callSummary = prepareJSONResponse();
        }
        return callSummary;
    }

    public CallSummary prepareJSONResponse(){
        EmployeePerformance [] managers = new EmployeePerformance[ConfigConstants.MANAGERS];
        EmployeePerformance [] senior_executives = new EmployeePerformance[ConfigConstants.SENIOR_EXECUTIVES];
        EmployeePerformance [] junior_executives = new EmployeePerformance[ConfigConstants.JUNIOR_EXECUTIVES];
        int resolvedCalls = 0;
        int unresolvedCalls = 0;
        int totalTimeTakenInMinutes = 0;
        for(int i=0;i<ConfigConstants.MANAGERS;i++){
            managers[i] = new EmployeePerformance(manager[i].getId(),manager[i].getTotalTimeTaken(),
                    manager[i].getAttendedCalls(),manager[i].getResolvedCalls(),manager[i].getEscalatedCalls());
            resolvedCalls += manager[i].getResolvedCalls();
            unresolvedCalls += manager[i].getEscalatedCalls();
            totalTimeTakenInMinutes += manager[i].getTotalTimeTaken();
        }
        for(int i=0;i<ConfigConstants.SENIOR_EXECUTIVES;i++){
            senior_executives[i] = new EmployeePerformance(seniorExecutive[i].getId(),seniorExecutive[i].getTotalTimeTaken(),
                    seniorExecutive[i].getAttendedCalls(),seniorExecutive[i].getResolvedCalls(),seniorExecutive[i].getEscalatedCalls());
            resolvedCalls += seniorExecutive[i].getResolvedCalls();
            totalTimeTakenInMinutes += seniorExecutive[i].getTotalTimeTaken();
        }
        for(int i=0;i<ConfigConstants.JUNIOR_EXECUTIVES;i++){
            junior_executives[i] = new EmployeePerformance(juniorExecutive[i].getId(),juniorExecutive[i].getTotalTimeTaken(),
                    juniorExecutive[i].getAttendedCalls(),juniorExecutive[i].getResolvedCalls(),juniorExecutive[i].getEscalatedCalls());
            resolvedCalls += juniorExecutive[i].getResolvedCalls();
            totalTimeTakenInMinutes += juniorExecutive[i].getTotalTimeTaken();
        }

        Performance performance = new Performance(managers,senior_executives,junior_executives);

        CallSummary summary = new CallSummary(calls.length,resolvedCalls,unresolvedCalls,totalTimeTakenInMinutes,performance);

        return summary;
    }
}
