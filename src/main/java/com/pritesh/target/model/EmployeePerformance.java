package com.pritesh.target.model;

/**
 * Created by pripatha on 11/5/2017.
 */
public class EmployeePerformance {
    private String id;
    private int timeTakenInMinutes;
    private int callsAttended;
    private int resolved;
    private int unresolvedOrEscalated;

    public EmployeePerformance(String id, int timeTakenInMinutes, int callsAttended, int resolved, int unresolved) {
        this.id = id;
        this.timeTakenInMinutes = timeTakenInMinutes;
        this.callsAttended = callsAttended;
        this.resolved = resolved;
        this.unresolvedOrEscalated = unresolved;
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

    public int getUnresolvedOrEscalated() {
        return unresolvedOrEscalated;
    }

    public void setUnresolvedOrEscalated(int unresolvedOrEscalated) {
        this.unresolvedOrEscalated = unresolvedOrEscalated;
    }
}
