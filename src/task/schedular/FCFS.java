
package task.schedular;

import java.util.Collections;
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
   
       for(int i=0; i<tasks.size(); i++)
       {
          for (int k=0; k<tasks.size()-i-1 ; k++)
          {
           if(tasks.get(k+1).getArrivalTime() < tasks.get(k).getArrivalTime() )
           {// sawp task no i , i+1 
           Collections.swap(tasks, k, k+1);
           }
           
          }
          
          
       }
          // now we need to set start and end time based on FCFS
          // |____A____|__C__|____D_____|_B_| 
          // start of c = end of A
          for(int index=0; index<tasks.size(); index++)
          {   
              
              if(index==0)
                  tasks.get(0).setStartTime(tasks.get(0).getArrivalTime());
              else 
                  tasks.get(index).setStartTime(tasks.get(index-1).getEndTime());
              
              
              
               tasks.get(index).setEndTime( tasks.get(index).getStartTime()+ tasks.get(index).getBurstTime());
          }
          
          
        List<Task> tasks_Setted =   UtileMethods.setParam(tasks) ;
  
        // start calculation using the parent methods 
        this.art = this.art_Calculation(tasks_Setted);   
        this.att = this.att_Calculation(tasks_Setted);  
        this.awt = this.awt_Calculation(tasks_Setted); 
        this.ufactor = this.ufactor_Calculation(tasks_Setted); 
        this.throughput = this.throughput_Calculation(tasks_Setted); 
        this.prop = this.prop_Calculation(tasks_Setted); 
      
      return tasks_Setted ;
   
   }

    

 
  
    
}
