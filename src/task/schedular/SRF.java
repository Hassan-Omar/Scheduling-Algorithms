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
       List<Task> queu = new ArrayList<>(); 
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
          for(int i=0;i<sum_Burst; i++)
          { int remaining = result_list.get(i).getBurstTime() ;
             
             if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                // queu.add(tasks_Arri.get(0)) ;
                 result_list.add(tasks_Arri.get(0)) ;
                  
                 
              }
              
              
              for(int j=0 ;j<sum_Burst; j++)
              { 
                  if(tasks_Arri.get(j).getArrivalTime()==j)
                  {
                   // stop the currnet task if the comming burst < remaining
                  if(tasks_Arri.get(j).getBurstTime() < remaining)
                  {
                      tasks_Arri.get(j).setStartTime(j);
                      
                      result_list.add(tasks_Arri.get(j));
                      // move the task in queu
                      tasks_Arri.get(j).setRemainTime(remaining);
                      tasks_Arri.add(j, tasks_Arri.get(j));
                      tasks_Arri.remove(0); 
                      // breaking inner loop
                      j+=sum_Burst ;
                  }
                 
                  
                  }
              
               remaining--;
              
              }
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
