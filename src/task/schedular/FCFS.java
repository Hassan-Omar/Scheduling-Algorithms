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
public class FCFS extends Algorithm {

    public FCFS() {
    }

    
    
   //----------------------------------------------------------------------- 
   public void drive (List<Task> tasks)
   {
   // now list the tasks based on arrival time 
   
       for(int i=0; i<tasks.size(); i++)
       {
          for (int k=0; k<tasks.size()-i-1 ; k++)
          {
           Task temp = new Task() ;
           temp = tasks.get(i) ; 
           tasks.set(i, tasks.get(i+1)) ; 
           
          }
          
          // now we need to set start and end time based on FCFS
          // |____A____|__C__|____D_____|_B_| 
          // start of c = end of A
          for(int k=0; k<tasks.size(); k++)
          {   
              
              if(k==0)
                  tasks.get(k).setStartTime(tasks.get(0).getArrivalTime());
              else 
                  tasks.get(k).setStartTime(tasks.get(k-1).getEndTime());
              
              
              
               tasks.get(k).setEndTime( tasks.get(k).getStartTime()+ tasks.get(k).getBurstTime());
           
          }
          
          
        List<Task> tasks_Setted =   UtileMethods.setParam(tasks) ;
       }
      
       // start calculation 
       
      this.awt = this.awt_Calculation(tasks) ; 
       System.out.println(awt);
       
     
   
   }

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
    public float art_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float att_Calculation(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
}
