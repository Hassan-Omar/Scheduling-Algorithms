package task.schedular;

import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author h.omar
 */
public class SRF extends Algorithm {
    
      static List<Task> out = new ArrayList<>();
    

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
       
       boolean flag = true ;
       
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
                     
                        System.out.println("task   "+ tasks_Arri.get(k).getName());
                   }
                }
              
             }catch(Exception e)
                     {  e.printStackTrace();
  
                     }
              // calling sorting method which sort based on remaining time
              // first one means this is the shortst remaining time 
            Task selectedTask = UtileMethods.sort_onRem(tasks_Arri).get(0);
            
             int busyTime = selectedTask.getBurstTime()-selectedTask.getRemainTime();
             // update remaining time 
            selectedTask.setRemainTime(selectedTask.getRemainTime() - 1);
            if(flag)
            {result_list.add(selectedTask);
             flag=false ; 
            }

            // delete if the task ended 
            if(selectedTask.getRemainTime()==0)
            {tasks_Arri.remove(selectedTask);
             // update the end time as the method is terminate  
             selectedTask.setEndTime(i+1);
             
             selectedTask.setStartTime(i-busyTime);
             
            flag = true ;
            }
       //  System.out.println("index  "+i+ "task  "+selectedTask.getName()+"start "+selectedTask.getStartTime() +"end " +selectedTask.getEndTime() +" rem "+selectedTask.getRemainTime()+"size"+tasks_Arri.size()); 
          
          }
          
        
  
      
        List<Task> tasks_Setted =   UtileMethods.setParam(result_list) ;
        // add idle tasks 
        List<Task> tasks_Setted_idle  = UtileMethods.insert_ilde(tasks_Setted) ;
        // copy data to temp 
        List<Task> temp = new ArrayList<>();
       for(int p=0; p<tasks_Setted.size(); p++)
       {
         RR.dataCopy(tasks_Setted.get(p),temp);
       }
      
       
      out = UtileMethods.preeptive_Orgnizer(tasks_Setted) ;
  
      
      
        // start calculation using the parent methods 
      this.art = this.art_Calculation(out);   
      this.att = this.att_Calculation(out);  
      this.awt = this.awt_Calculation(out); 
      this.ufactor = this.ufactor_Calculation(out); 
      this.throughput = this.throughput_Calculation(out); 
      this.prop = this.prop_Calculation(out); 
      
      return temp ;
   } 
    
}
