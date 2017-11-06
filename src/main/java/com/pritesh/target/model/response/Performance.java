package com.pritesh.target.model.response;

/**
 * Created by pripatha on 11/5/2017.
 */
public class Performance {
    ManagerPerformance manager;
    EmployeePerformance senior_executives[];
    EmployeePerformance junior_executives[];

    public Performance(ManagerPerformance manager, EmployeePerformance[] senior_executives, EmployeePerformance[] junior_executives) {
        this.manager = manager;
        this.junior_executives = junior_executives;
        this.senior_executives = senior_executives;
    }

    public ManagerPerformance getManager() {
        return manager;
    }
    public EmployeePerformance[] getSenior_executives() {
        return senior_executives;
    }
    public EmployeePerformance[] getJunior_executives() {
        return junior_executives;
    }


}
