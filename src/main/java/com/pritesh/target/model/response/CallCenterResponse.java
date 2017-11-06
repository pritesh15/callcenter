package com.pritesh.target.model.response;

/**
 * Created by pripatha on 11/5/2017.
 */
public class CallCenterResponse {
    private int num_of_calls;
    private int resolved;
    private int unresolved;
    private int totalTimeTakenInMinutes;
    private Performance performance;

    public CallCenterResponse(int num_of_calls, int resolved, int unresolved, int totalTimeTakenInMinutes, Performance performance) {
        this.num_of_calls = num_of_calls;
        this.resolved = resolved;
        this.unresolved = unresolved;
        this.totalTimeTakenInMinutes = totalTimeTakenInMinutes;
        this.performance = performance;
    }

    public int getNum_of_calls() {
        return num_of_calls;
    }

    public void setNum_of_calls(int num_of_calls) {
        this.num_of_calls = num_of_calls;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public int getUnresolved() {
        return unresolved;
    }

    public void setUnresolved(int unresolved) {
        this.unresolved = unresolved;
    }

    public int getTotalTimeTakenInMinutes() {
        return totalTimeTakenInMinutes;
    }

    public void setTotalTimeTakenInMinutes(int totalTimeTakenInMinutes) {
        this.totalTimeTakenInMinutes = totalTimeTakenInMinutes;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}
