
package task.schedular;

import java.util.List;

/**
 *
 * @author h.omar
 */
public class FCFS extends Algorithm {

    public FCFS() {
        
        this.setName("FCFS");
    }

    
    
   //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
          // now list the tasks based on arrival time 
          tasks=  UtileMethods.sort_onArrival(tasks);
     
          // now we need to set start and end time based on FCFS
          // |____A____|__C__|____D_____|_B_| 
          // start of c = end of A
          for(int index=0; index<tasks.size(); index++)
          {   
              // check if the previous task is ended ----> it's end time <= the arrivale time of the recived  
              if(index > 0)
              {
                if( tasks.get(index).getArrivalTime()>=tasks.get(index-1).getEndTime())
                  tasks.get(index).setStartTime(tasks.get(index).getArrivalTime());
              else 
                  tasks.get(index).setStartTime(tasks.get(index-1).getEndTime());
              
              }
              else
              {   // this is the first task 
                  // which make sense we will start counting time when we recive it 
                  tasks.get(0).setStartTime(tasks.get(0).getArrivalTime());
              }
              
               // end time = start + burst  // make sense as the task is non_preemptive
               tasks.get(index).setEndTime( tasks.get(index).getStartTime()+ tasks.get(index).getBurstTime());
          }
          
        // setting tasks calculation 
        List<Task> tasks_Setted =   UtileMethods.setParam(tasks) ;
        // add idle tasks 
        List<Task> tasks_Setted_idle  = UtileMethods.insert_ilde(tasks_Setted) ;
        
  
        // start calculation using the parent methods 
        this.art = this.art_Calculation(tasks_Setted_idle);   
        this.att = this.att_Calculation(tasks_Setted_idle);  
        this.awt = this.awt_Calculation(tasks_Setted_idle); 
        // ufactor should be calculated for tasks_Setted_idle as idle affects on total time  
        this.ufactor = this.ufactor_Calculation(tasks_Setted_idle); 
        // throughput should be calculated for recived tasks 
        this.throughput = this.throughput_Calculation(tasks_Setted_idle); 
        this.prop = this.prop_Calculation(tasks_Setted); 
      
      return tasks_Setted_idle ;
   
   }

    

 
  
    
}
