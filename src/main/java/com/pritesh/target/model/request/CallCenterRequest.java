package com.pritesh.target.model.request;

import java.util.List;

/**
 * Created by pripatha on 11/6/2017.
 */
public class CallCenterRequest {
    private String number_of_calls;
    private List<String> je;
    private List<String> se;
    private String mgr;


    public int getNumber_of_calls() {
        return Integer.parseInt(number_of_calls);
    }

    public void setNumber_of_calls(String number_of_calls) {
        this.number_of_calls = number_of_calls;
    }

    public List<String> getJe() {
        return je;
    }

    public void setJe(List<String> je) {
        this.je = je;
    }

    public List<String> getSe() {
        return se;
    }

    public void setSe(List<String> se) {
        this.se = se;
    }

    public String getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }
}
