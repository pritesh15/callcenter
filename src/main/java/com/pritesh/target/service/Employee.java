package com.pritesh.target.service;

import com.pritesh.target.model.Call;

import java.util.Queue;

/**
 * Created by pripatha on 11/4/2017.
 */
public class Employee implements Runnable{
    private Call[] calls;
    private Queue<Call> q1;
    private Queue<Call> q2;
    private String id;
    private int thresholdCalls;
    private int durationLimit;
    private int resolvedCalls;
    private int attendedCalls;
    private int escalatedCalls;
    private int totalTimeTaken;
    private volatile boolean running = true;

    public Employee(Call[] calls,Queue<Call> q1, Queue<Call> q2, String id, int thresholdCalls, int durationLimit) {
        this.calls =calls;
        this.q1 = q1;
        this.q2 = q2;
        this.id = id;
        this.thresholdCalls = thresholdCalls;
        this.durationLimit = durationLimit;
    }

    public Call[] getCalls() {
        return calls;
    }

    public void setCalls(Call[] calls) {
        this.calls = calls;
    }

    public int getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(int totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    public int getDurationLimit() {
        return durationLimit;
    }

    public Queue<Call> getQ1() {
        return q1;
    }

    public Queue<Call> getQ2() {
        return q2;
    }

    public int getThresholdCalls(){
        return thresholdCalls;
    }

    public int getResolvedCalls() {
        return resolvedCalls;
    }

    public void setResolvedCalls(int resolvedCalls) {
        this.resolvedCalls = resolvedCalls;
    }

    public int getAttendedCalls() {
        return attendedCalls;
    }

    public void setAttendedCalls(int attendedCalls) {
        this.attendedCalls = attendedCalls;
    }

    public String getId() {
        return id;
    }

    public int getEscalatedCalls() {
        return escalatedCalls;
    }

    public void setEscalatedCalls(int escalatedCalls) {
        this.escalatedCalls = escalatedCalls;
    }

    public void terminate() {
        running = false;
    }

    public void run() {
        try {
            while (this.getAttendedCalls() <= this.getThresholdCalls() && running) {
                if (!this.getQ1().isEmpty()) {
                    Call call;
                    synchronized (this.getQ1()) {
                        call = this.getQ1().poll();
                    }
                    call.setDuration(this.getCalls()[this.getAttendedCalls()].getDuration());
                    CallProcessingAndEscalation callProcessingAndEscalation = new CallProcessingAndEscalation();
                    callProcessingAndEscalation.processCall(this, call);
                } else {
                    Thread.sleep(5);
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            running = false;
        }catch(InterruptedException ex){
            running = false;
            ex.printStackTrace();
        }
    }

}
