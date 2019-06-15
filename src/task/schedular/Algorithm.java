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
 // THIS to calculate average Waiting time
 public  float awt_Calculation (List<Task> tasks)
 {
 
        int totalWaitingTime = 0 ;
        for(int i=0 ; i<tasks.size() ; i++)
        {
        // calculate total time by looping on all process 
        totalWaitingTime += tasks.get(i).getWaitTime();
        }
        // average waiting time  = total time / num of tasks 
        float avWT =(float)totalWaitingTime/tasks.size() ; 
        return  avWT; 
 
 
 }
 //____________________________________________________________________________
  // THIS to calculate Response Waiting time
 public  float art_Calculation (List<Task> tasks)
  {
 
        int totalResponseTime = 0 ;
        for(int i=0 ; i<tasks.size() ; i++)
        {
        // calculate total time by looping on all process 
        totalResponseTime += tasks.get(i).getResponseTime();
        }
        // average waiting time  = total time / num of tasks 
        float avRT =(float)totalResponseTime/tasks.size() ; 
        return  avRT; 
 
 
 }
 //____________________________________________________________________________
  // THIS to calculate average Turn Around time
 public  float att_Calculation (List<Task> tasks) {
 
        int totalTurnTime = 0 ;
        for(int i=0 ; i<tasks.size() ; i++)
        {
        // calculate total time by looping on all process 
        totalTurnTime += tasks.get(i).getTotalATime();
        }
        // average waiting time  = total time / num of tasks 
        float avTT =(float)totalTurnTime/tasks.size() ; 
        return  avTT; 
 
 
 }
    
}
