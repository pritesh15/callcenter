package com.pritesh.target.service;

import com.pritesh.target.model.request.CallCenterRequest;
import com.pritesh.target.model.ConfigConstants;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.pritesh.target.model.Call;

/**
 * Created by pripatha on 11/5/2017.
 */
public class CallService {
    Queue<Call> juniorQueue;
    Queue<Call> seniorQueue;
    Queue<Call> managerQueue;
    Queue<Call> unresolvedQueue;
    Employee[] juniorExecutive;
    Employee[] seniorExecutive;
    Employee manager[];
    int thresholdCalls[];
    private CallCenterRequest request;
    public CallService(CallCenterRequest request,Queue<Call> juniorQueue, Queue<Call> seniorQueue, Queue<Call> managerQueue,
                       Queue<Call> unresolvedQueue, Employee[] juniorExecutive, Employee[] seniorExecutive,
                       Employee manager[], int thresholdCalls[]) {
        this.request = request;
        this.juniorQueue = juniorQueue;
        this.seniorQueue = seniorQueue;
        this.managerQueue = managerQueue;
        this.unresolvedQueue = unresolvedQueue;
        this.juniorExecutive = juniorExecutive;
        this.seniorExecutive = seniorExecutive;
        this.manager = manager;
        this.thresholdCalls = thresholdCalls;
    }

    public boolean serviceCalls(){
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i=0;i<ConfigConstants.JUNIOR_EXECUTIVES;i++){
            String id = "je"+Integer.toString(i+1);
            String jeCalls[] = request.getJe().get(i).split(",");
            Call[] calls = new Call[jeCalls.length];
            for(int j=0;j<jeCalls.length;j++){
                calls[j] = new Call(Integer.parseInt(jeCalls[j]));
            }
            juniorExecutive[i] = new Employee(calls,juniorQueue,seniorQueue,id,thresholdCalls[0],ConfigConstants.DURATION_LIMIT_JE);
            es.execute(juniorExecutive[i]);
        }

        for(int i=0;i<ConfigConstants.SENIOR_EXECUTIVES;i++){
            String id = "se"+Integer.toString(i+1);
            String seCalls[] = request.getSe().get(i).split(",");
            Call[] calls = new Call[seCalls.length];
            for(int j=0;j<seCalls.length;j++){
                calls[j] = new Call(Integer.parseInt(seCalls[j]));
            }
            seniorExecutive[i] =new Employee(calls,seniorQueue,managerQueue,id,thresholdCalls[0],ConfigConstants.DURATION_LIMIT_SE);
            es.execute(seniorExecutive[i]);
        }

            String id = "mgr";
            String mgrCalls[] = request.getMgr().split(",");
            Call[] calls = new Call[mgrCalls.length];
            for(int j=0;j<mgrCalls.length;j++){
                calls[j] = new Call(Integer.parseInt(mgrCalls[j]));
            }
            manager[0] = new Employee(calls,managerQueue, unresolvedQueue,id, thresholdCalls[1], ConfigConstants.DURATION_LIMIT_MGR);

            es.execute(manager[0]);


        es.shutdown();

        return waitForThreadTermination(es);
    }

    public boolean waitForThreadTermination(ExecutorService es){
        boolean finished = false;
        try {
            while (!finished){
                if (juniorQueue.isEmpty() && seniorQueue.isEmpty() && managerQueue.isEmpty()) {
                    for (int i = 0; i < ConfigConstants.JUNIOR_EXECUTIVES; i++) {
                        juniorExecutive[i].terminate();

                    }
                    for (int i = 0; i < ConfigConstants.SENIOR_EXECUTIVES; i++) {
                        seniorExecutive[i].terminate();
                    }

                        manager[0].terminate();

                    finished = true;
                } else {
                    finished = es.awaitTermination(2, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for thread termination");
            e.printStackTrace();
        }
        return finished;
    }
}
