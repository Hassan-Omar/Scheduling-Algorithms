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
public abstract class Algorithm {
    String name ; 
    float awt , art ,att , ufactor , throughput , prop ; 

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAwt() {
        return awt;
    }

    public void setAwt(float awt) {
        this.awt = awt;
    }

    public float getArt() {
        return art;
    }

    public void setArt(float art) {
        this.art = art;
    }

    public float getAtt() {
        return att;
    }

    public void setAtt(float att) {
        this.att = att;
    }

    public float getUfactor() {
        return ufactor;
    }

    public void setUfactor(float ufactor) {
        this.ufactor = ufactor;
    }

    public float getThroughput() {
        return throughput;
    }

    public void setThroughput(float throughput) {
        this.throughput = throughput;
    }

    public float getProp() {
        return prop;
    }

    public void setProp(float prop) {
        this.prop = prop;
    }
    
 public abstract float ufactor_Calculation (List<Task> tasks);
 
 
 public abstract float throughput_Calculation (List<Task> tasks);
 
 
 public abstract float prop_Calculation (List<Task> tasks);
 
 
 //____________________________________________________________________________ 
 public  float awt_Calculation (List<Task> tasks)
 {
 
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
 //____________________________________________________________________________
 public abstract float art_Calculation (List<Task> tasks);
 public abstract float att_Calculation (List<Task> tasks);
    
}
