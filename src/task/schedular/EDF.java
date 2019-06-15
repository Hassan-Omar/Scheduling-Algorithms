package task.schedular;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author h.omar
 */
public class EDF extends Algorithm {
    
     public EDF() {
        
        this.setName("EDF");
    }

    
    
   //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
         
      // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = UtileMethods.sort_onArrival(tasks) ; // this just to know which we will use to start
       List<Task> queu = UtileMethods.sort_onBurst(tasks) ; 
       List<Task> result_list = new ArrayList<>() ;
          // now we need to set start and end time based on EDF
          // |__C__|____A____|____D_____|_B_| 
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
