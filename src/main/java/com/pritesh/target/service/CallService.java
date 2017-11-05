package com.pritesh.target.service;

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
    Employee[] manager;
    int thresholdCalls;

    public CallService(Queue<Call> juniorQueue, Queue<Call> seniorQueue, Queue<Call> managerQueue,
                       Queue<Call> unresolvedQueue, Employee[] juniorExecutive, Employee[] seniorExecutive,
                       Employee[] manager, int thresholdCalls) {
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
            juniorExecutive[i] = new Employee(juniorQueue,seniorQueue,id,thresholdCalls,ConfigConstants.DURATION_LIMIT_JE);
            es.execute(juniorExecutive[i]);
        }

        for(int i=0;i<ConfigConstants.SENIOR_EXECUTIVES;i++){
            String id = "se"+Integer.toString(i+1);
            seniorExecutive[i] =new Employee(seniorQueue,managerQueue,id,thresholdCalls,ConfigConstants.DURATION_LIMIT_SE);
            es.execute(seniorExecutive[i]);
        }

        for(int i=0;i< ConfigConstants.MANAGERS;i++) {
            String id = "mgr"+Integer.toString(i+1);
            manager[i] = new Employee(managerQueue, unresolvedQueue,id, thresholdCalls, ConfigConstants.DURATION_LIMIT_MGR);

            es.execute(manager[i]);
        }

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
                    for (int i=0; i < ConfigConstants.MANAGERS;i++) {
                        manager[i].terminate();
                    }
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
