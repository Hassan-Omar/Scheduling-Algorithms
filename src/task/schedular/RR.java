package task.schedular;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author h.omar
 */
public class RR extends Algorithm {
 int q=1 ; 
    public RR() {
        this.setName("RR");
       // read q from user 
       String s = JOptionPane.showInputDialog("Enter Q");
         q =(int)Float.parseFloat(s) ;
    }
    

  //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
      
       // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = UtileMethods.sort_onArrival(tasks) ; // this just to know which we will use to start
       List<Task> queue = new ArrayList<>(); 
       List<Task> result_list = new ArrayList<>() ;
       
       // this comming loop to 
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
       
       }
                     int i =0 ;

          // now we need to set start and end time based on RR
          // |_A_|_C_|_A_|_B_|_D_|_A_|_C_|_D_|_A_|_C_|_A_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int index=0;index<sum_Burst; index+=q)
          {
          if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                 queue.add(tasks_Arri.get(0));
                   // remain time = burst - q
                queue.get(0).setRemainTime( queue.get(0).getBurstTime()-q);
       
                 result_list.add(tasks_Arri.get(0)) ;
                  
                 
              }
             
          if(UtileMethods.getArrived(tasks, index)!=null)
          {
           // if we are here this means we recived a new task
           queue.add(UtileMethods.getArrived(tasks, index));
          
          }
          
          
          
          
          
            System.out.println(i);
        
            
            if(i>0)
          {
            // remain time = burst - q
          queue.get(0).setRemainTime( queue.get(0).getBurstTime()-q);
          result_list.add(queue.get(0));
          
          
          // check if the task @ end
           if(queue.get(0).getRemainTime()==0)
          { 
          queue.remove(queue.get(0));
          System.out.println("1st Q deleted"); 
          }
        
         // roatate the queue
         queue.add(queue.remove(0));
         
        
         }
            
         
          
        
          // end = start + q
            result_list.get(i).setEndTime( result_list.get(i).getStartTime()+q );

         
         // System.out.println("end "+  result_list.get(i).getEndTime() +"start"+  result_list.get(i).getStartTime());
         i++;
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
