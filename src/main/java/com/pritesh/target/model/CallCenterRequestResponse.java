package com.pritesh.target.model;

import com.pritesh.target.model.request.CallCenterRequest;
import com.pritesh.target.model.response.CallCenterResponse;
import com.pritesh.target.model.response.EmployeePerformance;
import com.pritesh.target.model.response.ManagerPerformance;
import com.pritesh.target.model.response.Performance;
import com.pritesh.target.service.CallService;
import com.pritesh.target.service.Employee;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by pripatha on 11/5/2017.
 */
public class CallCenterRequestResponse {
    private Queue<Call> juniorQueue;
    private Queue<Call> seniorQueue;
    private Queue<Call> managerQueue;
    private Queue<Call> unresolvedQueue;
    private Employee juniorExecutive[];
    private Employee seniorExecutive[];
    private Employee manager[];
    private int thresholdCalls[];
    private CallCenterRequest request;

    public CallCenterRequestResponse(CallCenterRequest request){
        this.request = request;
        juniorQueue = new LinkedList<Call>();
        seniorQueue = new LinkedList<Call>();
        managerQueue = new LinkedList<Call>();
        unresolvedQueue = new LinkedList<Call>();
        juniorExecutive = new Employee[ConfigConstants.JUNIOR_EXECUTIVES];
        seniorExecutive = new Employee[ConfigConstants.SENIOR_EXECUTIVES];
        manager = new Employee[1];
        thresholdCalls = new int[2];
    }
    public CallCenterResponse processCalls(){
        for(int i=0;i<request.getNumber_of_calls();i++){
            juniorQueue.add(new Call(0));
        }
        thresholdCalls[0] = request.getNumber_of_calls()/ConfigConstants.JUNIOR_EXECUTIVES + ConfigConstants.SENIOR_EXECUTIVES;
        thresholdCalls[1] = request.getNumber_of_calls();
        CallService service = new CallService(request,juniorQueue,seniorQueue,managerQueue,unresolvedQueue,juniorExecutive,seniorExecutive,manager,thresholdCalls);
        boolean allCallsServiced = service.serviceCalls();
        CallCenterResponse callCenterResponse = null;
        if(allCallsServiced){
             callCenterResponse = prepareJSONResponse();
        }
        return callCenterResponse;
    }

    public CallCenterResponse prepareJSONResponse(){
        ManagerPerformance managers;
        EmployeePerformance [] senior_executives = new EmployeePerformance[ConfigConstants.SENIOR_EXECUTIVES];
        EmployeePerformance [] junior_executives = new EmployeePerformance[ConfigConstants.JUNIOR_EXECUTIVES];
        int resolvedCalls = 0;
        int unresolvedCalls = 0;
        int totalTimeTakenInMinutes = 0;

            managers = new ManagerPerformance(manager[0].getId(),manager[0].getTotalTimeTaken(),
                    manager[0].getAttendedCalls(),manager[0].getResolvedCalls(),manager[0].getEscalatedCalls());
            resolvedCalls += manager[0].getResolvedCalls();
            totalTimeTakenInMinutes += manager[0].getTotalTimeTaken();

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
        unresolvedCalls = request.getNumber_of_calls()-resolvedCalls;
        Performance performance = new Performance(managers,senior_executives,junior_executives);

        CallCenterResponse summary = new CallCenterResponse(request.getNumber_of_calls(),resolvedCalls,unresolvedCalls,totalTimeTakenInMinutes,performance);

        return summary;
    }
}
