package task.schedular;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author h.omar
 */
public class SRF extends Algorithm {


  //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
      
       // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = UtileMethods.sort_onArrival(tasks) ; // this just to know which we will use to start
       List<Task> queue = new ArrayList<>() ;
       List<Task> result_list = new ArrayList<>() ;
       
       // this comming loop to  
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
       
       }
       
          // now we need to set start and end time based on SRF
          // |___A___|__C__|_A_|_B_|____D____|_C_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int index=0;index<sum_Burst;index++)
          {   // count tasks ;
              int i =0 ;
              
              if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                 tasks_Arri.get(0).setRemainTime( tasks_Arri.get(0).getBurstTime());
                 queue.add(tasks_Arri.get(0)) ;
                 result_list.add(queue.get(0)) ;
              }
            
             if(UtileMethods.getArrived(tasks, i)!=null) 
             {
              if(UtileMethods.getArrived(tasks, i).getBurstTime()<result_list.get(i).getRemainTime())
              queue.add(tasks.get(i)) ;
              queue.get(0).setStartTime(index);
              result_list.add(queue.get(0)) ;
              
            i++ ;  
             }
             
             if(queue.get(0).getRemainTime()==0)
                 queue.remove(0);
             
             
             queue.get(0).setRemainTime(queue.get(0).getRemainTime() - 1);
             
              
 
           // end time = start + busy = start + burst - remaining = end of privous + burst - remaining    
 result_list.get(i).setEndTime(result_list.get(i-1).getEndTime()+ ( result_list.get(i-1).getBurstTime()- result_list.get(i-1).getRemainTime()));   
          System.out.println("end "+  result_list.get(i).getEndTime() +"start"+  result_list.get(i).getStartTime());
         
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
