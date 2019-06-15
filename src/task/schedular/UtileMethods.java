package task.schedular;
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
       }
    return tasks ; 
    }
    
}
