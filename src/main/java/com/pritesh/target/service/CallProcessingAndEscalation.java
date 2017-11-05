package com.pritesh.target.service;

import com.pritesh.target.model.Call;

/**
 * Created by pripatha on 11/5/2017.
 */
public class CallProcessingAndEscalation {

    public static void processCall(Employee employee,Call call){
        employee.setAttendedCalls(employee.getAttendedCalls()+1);
        try {
            int duration = call.getDuration();
            Thread.sleep(employee.getDurationLimit());
            if(duration > employee.getDurationLimit()){
                employee.setTotalTimeTaken(employee.getTotalTimeTaken()+employee.getDurationLimit());
                escalateCall(employee,call);
            }
            else{
                employee.setResolvedCalls(employee.getResolvedCalls()+1);
                employee.setTotalTimeTaken(employee.getTotalTimeTaken()+call.getDuration());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void escalateCall(Employee employee,Call call){
        employee.setEscalatedCalls(employee.getEscalatedCalls()+1);
        synchronized (employee.getQ2()){
            employee.getQ2().add(call);
        }
    }
}
