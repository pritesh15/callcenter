package com.pritesh.target.model;

import com.pritesh.target.service.Employee;

/**
 * Created by pripatha on 11/5/2017.
 */
public class Performance {
    EmployeePerformance managers[];
    EmployeePerformance senior_executives[];
    EmployeePerformance junior_executives[];

    public Performance(EmployeePerformance[] managers, EmployeePerformance[] senior_executives, EmployeePerformance[] junior_executives) {
        this.managers = managers;
        this.junior_executives = junior_executives;
        this.senior_executives = senior_executives;
    }

    public EmployeePerformance[] getManager() {
        return managers;
    }
    public EmployeePerformance[] getSenior_executives() {
        return senior_executives;
    }
    public EmployeePerformance[] getJunior_executives() {
        return junior_executives;
    }


}
