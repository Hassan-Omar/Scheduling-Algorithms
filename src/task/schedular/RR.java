/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.schedular;

import java.util.List;

/**
 *
 * @author h.omar
 */
public class RR extends Algorithm {

    

    @Override
    public float ufactor_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float throughput_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float prop_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float awt_Calculation(List<Task> tasks) {
        int totalWaitingTime = 0 ;
        int numOfTasks = 0 ; 
        for(int i=0 ; i<tasks.size() ; i++)
        {
        // calculate total time by looping on all process 
        totalWaitingTime += tasks.get(i).getWaitTime();
        // increase number of tasks
        numOfTasks++;
        }
        // average waiting time  = total time / num of tasks 
 return totalWaitingTime/numOfTasks ;
    }

    @Override
    public float art_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float att_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
