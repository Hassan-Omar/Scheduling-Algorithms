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
       List<Task> queu = UtileMethods.sort_onDeadline(tasks) ; 
       List<Task> result_list = new ArrayList<>() ;
          // now we need to set start and end time based on EDF
          // |__C__|____A____|____D_____|_B_| 
          // start of TASK(i+1) = end of  TASK(i) 
          for(int index=0; index<tasks.size()+2; index++)
          {   
              //System.out.println(index);
              if(index==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                 result_list.add(tasks_Arri.get(0)) ;
                 queu.remove(tasks_Arri.get(0));
               
              }
              else 
              {  
                   // check of the first task in queue 
                  if(queu.get(0).getArrivalTime() < result_list.get(index-1).getEndTime() )
                  {result_list.add(queu.get(0)) ;
                   queu.remove(queu.get(0));
                   result_list.get(index).setStartTime( result_list.get(index-1).getEndTime());
                  }
                  
              }
               // end = start + burst
               result_list.get(index).setEndTime( result_list.get(index).getStartTime()+ result_list.get(index).getBurstTime());
                   //System.out.println(result_list.get(index).getStartTime() + "  " + result_list.get(index).getEndTime());

          }
           
          
          
          
          
        List<Task> tasks_Setted =   UtileMethods.setParam(result_list) ;

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
