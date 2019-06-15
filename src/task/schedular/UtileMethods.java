package task.schedular;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author h.omar
 */
public class UtileMethods {
    // method to set this param 
    // total response time = start time - arrival time
    // total wait time = end time - arraival time - burst time
    // total turn arround time = end time - arraival time

    public static List<Task> setParam (List<Task> tasks)
    {
    
    
       for(int i=0 ; i<tasks.size() ; i++)
       {
          int end = tasks.get(i).getEndTime() ; 
          int start = tasks.get(i).getStartTime();
          int arrival =tasks.get(i).getArrivalTime();
          int burst =tasks.get(i).getBurstTime();
          
           // setting pararms
           tasks.get(i).setResponseTime(start-arrival) ; 
           tasks.get(i).setWaitTime(end-arrival-burst) ;
           tasks.get(i).setTotalATime(end-arrival) ;
           
           
           //System.out.println(" t  " +tasks.get(i).getWaitTime() );  
       }
    return tasks ; 
    }
    
 //=============================================================================   
    // this task just view the data 
   public static  String printer (List<Task> tasks)
    {
    String result = "TASK       START_TIME       END_TIME       DURATION       STATUS  \r\n";
    
       for(int i=0 ; i<tasks.size() ; i++)
       {
           result += tasks.get(i).getNum() + "              " ;
           result += tasks.get(i).getStartTime() + "              " ; 
           result += tasks.get(i).getEndTime()+ "             " ; 
           result += ( tasks.get(i).getEndTime()-tasks.get(i).getStartTime() ) + "            " ; 
           // WHAT IS THE STATUS 
           result+="\r\n" ; 
       }
        
        
        
    return result ;
    
    }
   
//==============================================================================
   //this method sort the taskes based on arrivall time
   
   public static List<Task> sort_onArrival(List<Task> tasks)
   {
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
   return tasks ; 
   }
//==============================================================================
   //this method sort the taskes based on arrivall time
   
   public static List<Task> sort_onBurst(List<Task> tasks)
   {
     for(int i=0; i<tasks.size(); i++)
       {
          for (int k=0; k<tasks.size()-i-1 ; k++)
          {
           if(tasks.get(k+1).getBurstTime()< tasks.get(k).getBurstTime())
           {// sawp task no i , i+1 
           Collections.swap(tasks, k, k+1);
           }
           
          }
          
          
       }
   return tasks ; 
   }
   //==============================================================================
   //this method sort the taskes based on deadline time
   
   public static List<Task> sort_onDeadline(List<Task> tasks)
   {
     for(int i=0; i<tasks.size(); i++)
       {
          for (int k=0; k<tasks.size()-i-1 ; k++)
          {
           if(tasks.get(k+1).getDeadLine()< tasks.get(k).getDeadLine())
           {// sawp task no i , i+1 
           Collections.swap(tasks, k, k+1);
           }
           
          }
          
       }
   return tasks ; 
   }
}
