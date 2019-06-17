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
       List<Task> tasks_Arri = new ArrayList<>() ; // this just to know which we will use to start
      
       List<Task> result_list = new ArrayList<>() ;
       // now we need to set start and end time based on EDF
          // |__C__|____A____|____D_____|_B_| 
          // start of TASK(i+1) = end of  TASK(i) 
       // this comming loop to get total burst time & inintilize remain timee
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
        tasks.get(j).setRemainTime(tasks.get(j).getBurstTime());
       }  
       
        tasks = UtileMethods.sort_onArrival(tasks);
       float [] rems = new float [sum_Burst];
       boolean flag = true ;
          
          for(int i=0; i<sum_Burst; i++)
          {   
             
              
             try{
                  // loop in tasks to check which is deliverd 
              for(int k=0;k<tasks.size();k++)
               { 
                   if(tasks.get(k).getArrivalTime()== i )
                   {   
                       tasks_Arri.add(tasks.get(k));
                      //System.out.print("Task Recived = "+tasks.get(k).getName());
                   }
                }
              
             }catch(Exception e)
                     {  e.printStackTrace();
  
                     }
             
       
             
            Task selectedTask = new Task() ;
            // calling sorting method which sort based on remaining time
            // first one means this is the shortst deadline time but if we have only one task we don't need to sort  
            
            rems[i]=selectedTask.getRemainTime();
            if(tasks_Arri.size()==1) 
                  selectedTask  =tasks_Arri.get(0); 
             else if(tasks_Arri!=null)
             {   // if we don't use comming part this will be look like preemptive EDF
              if (UtileMethods.sort_onDeadline(tasks_Arri).get(0).getDeadLine() < result_list.get(result_list.size()-1).getDeadLine() && !result_list.get(result_list.size()-1).getFlag())
                selectedTask = result_list.get(result_list.size()-1);
               else 
                selectedTask = UtileMethods.sort_onDeadline(tasks_Arri).get(0);
   
             }
           
            // update remaining time 
            selectedTask.setRemainTime(selectedTask.getRemainTime() - 1);
            if(flag)
            {   
                result_list.add(selectedTask);
                selectedTask.setStartTime(i);
                System.out.println("added"+selectedTask.getName());
             flag=false ; 
            }

            // delete if the task ended 
            if(selectedTask.getRemainTime()==0)
            {tasks_Arri.remove(selectedTask);
             // update the end time as the method is terminate  
             selectedTask.setEndTime(i+1);
             
             flag = true ;
             result_list.get(result_list.size()-1).setFlag(true);
            }

            
         System.out.println("index  "+i+ "task  "+selectedTask.getName()+"start "+selectedTask.getStartTime() +"end " +selectedTask.getEndTime() +" rem "+selectedTask.getRemainTime()+"size"+tasks_Arri.size()); 
         
        
          }
           
          
          
          
          
        List<Task> tasks_Setted =   UtileMethods.setParam(result_list) ;
        // add idle tasks 
     //   List<Task> tasks_Setted_idle  = UtileMethods.insert_ilde(tasks_Setted) ;
       List<Task> tasks_Setted_idle  =tasks_Setted;
        
  
        // start calculation using the parent methods 
        this.art = this.art_Calculation(tasks_Setted_idle);   
        this.att = this.att_Calculation(tasks_Setted_idle);  
        this.awt = this.awt_Calculation(tasks_Setted_idle); 
        // ufactor should be calculated for tasks_Setted_idle as idle affects on total time  
        this.ufactor = this.ufactor_Calculation(tasks_Setted_idle); 
        // throughput should be calculated for recived tasks 
        this.throughput = this.throughput_Calculation(tasks_Setted_idle); 
        this.prop = this.prop_Calculation(tasks_Setted); 
      
      return tasks_Setted_idle ;
   }

    

 
  
    
}
