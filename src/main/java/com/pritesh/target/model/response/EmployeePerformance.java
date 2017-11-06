package com.pritesh.target.model.response;

/**
 * Created by pripatha on 11/5/2017.
 */
public class EmployeePerformance {
    private String id;
    private int timeTakenInMinutes;
    private int callsAttended;
    private int resolved;
    private int escalated;

    public EmployeePerformance(String id, int timeTakenInMinutes, int callsAttended, int resolved, int unresolved) {
        this.id = id;
        this.timeTakenInMinutes = timeTakenInMinutes;
        this.callsAttended = callsAttended;
        this.resolved = resolved;
        this.escalated = unresolved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimeTakenInMinutes() {
        return timeTakenInMinutes;
    }

    public void setTimeTakenInMinutes(int timeTakenInMinutes) {
        this.timeTakenInMinutes = timeTakenInMinutes;
    }

    public int getCallsAttended() {
        return callsAttended;
    }

    public void setCallsAttended(int callsAttended) {
        this.callsAttended = callsAttended;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public int getEscalated() {
        return escalated;
    }

    public void setEscalated(int escalated) {
        this.escalated = escalated;
    }
}
