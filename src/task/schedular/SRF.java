package task.schedular;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author h.omar
 */
public class SRF extends Algorithm {

    public SRF() {
        this.name="SRF" ;
    }


  //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
      
       // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = new ArrayList<>() ; // this just to know which we will use to start
      
       List<Task> result_list = new ArrayList<>() ;
       
       // this comming loop to get total burst time & inintilize remain timee
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
        tasks.get(j).setRemainTime(tasks.get(j).getBurstTime());
       }  
       
        tasks = UtileMethods.sort_onArrival(tasks);
       
       boolean flag = false ;
       
          // now we need to set start and end time based on SRF
          // |___A___|__C__|_A_|_B_|____D____|_C_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int i=0;i<sum_Burst;i++)
          {   
             try{
                  // loop in tasks to check which is deliverd 
              for(int k=0;k<tasks.size();k++)
               { 
                   if(tasks.get(k).getArrivalTime()== i )
                   {   
                       tasks_Arri.add(tasks.get(k));
                       flag = true ;
                        System.out.println("task   "+ tasks_Arri.get(k).getName());
                   }
                }
              
             }catch(Exception e)
                     {  e.printStackTrace();
  
                     }
              // calling sorting method which sort based on remaining time
              // first one means this is the shortst remaining time 
            Task selectedTask = UtileMethods.sort_onRem(tasks_Arri).get(0);
            
            
             // update remaining time 
            selectedTask.setRemainTime(selectedTask.getRemainTime() - 1);
            
            result_list.add(selectedTask);
            // delete if the task ended 
            if(selectedTask.getRemainTime()==0)
            {tasks_Arri.remove(selectedTask);
             // update the end time as the method is terminate  
             selectedTask.setEndTime(i+1);
             System.out.println("removed  ");
            }
         System.out.println("index  "+i+ "task  "+selectedTask.getName()+"start "+selectedTask.getStartTime() +"end " +selectedTask.getEndTime() +" rem "+selectedTask.getRemainTime()+"size"+tasks_Arri.size()); 
          
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
