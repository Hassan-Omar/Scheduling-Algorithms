package task.schedular;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
           result += tasks.get(i).getName()+ "              " ;
           result += tasks.get(i).getStartTime() + "              " ; 
           result += tasks.get(i).getEndTime()+ "             " ; 
           result += ( tasks.get(i).getEndTime()-tasks.get(i).getStartTime() ) + "            " ; 
           // WHAT IS THE STATUS 
           result+="\r\n\r\n" ;
     
           
       }
        result+= "\r\n" +"      ____________________________________       " + "\r\n\r\n\r\nGantt Chart \r\n" ; 
        // drawing the gantt chart 
       for(int k=0; k<tasks.size(); k++)
       { String temp= ""; 
         result+="|";
         int busy = tasks.get(k).getEndTime()- tasks.get(k).getStartTime() ;
         for(int j=0; j<(busy/2) ;j++)
         {
           temp+="_";
         }
         
         result +=temp+tasks.get(k).getName()+temp;
         
      
       }
         result+="|\r\n\r\n" ;
        
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
     
     if(tasks!=null)
     {
       tasks.get(0).setStartTime(tasks.get(0).getArrivalTime());
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
   
    
//==============================================================================
   //this method sort the taskes based on remaining time
   
   public static List<Task> sort_onRem(List<Task> tasks)
   {
     for(int i=0; i<tasks.size(); i++)
       {
          for (int k=0; k<tasks.size()-i-1 ; k++)
          {
           if(tasks.get(k+1).getRemainTime() < tasks.get(k).getRemainTime())
           {// sawp task no i , i+1 
           Collections.swap(tasks, k, k+1);
           }
           
          }
          
          
       }
   return tasks ; 
   } 
   
   //==============================================================================
   //this method to inseryt idle task in this list 
   
   public static List<Task> insert_ilde(List<Task> tasks)
   {
        int start_i = 0 , end =0; 
     for(int i=1; i<tasks.size(); i++)
       {
           start_i = tasks.get(i).getStartTime();
           end = tasks.get(i-1).getEndTime();
            // if statrt(i) == end(i-1) there are no problem
           if( !(start_i==end) )
           {   // create idle sarted and arrived @ end , ends @ start_i 
               Task idle = new Task() ; 
               idle.setEndTime(start_i);
               // idle.arrived  as same as idle.start 
               idle.setArrivalTime(end);
               idle.setStartTime(end);
                // burst = end - start
              idle.setBurstTime(start_i - end);
              idle.setName("!");
              // large number as we don't need to carry this 
              idle.setDeadLine(999999999); 
            tasks.add(i, idle);
         }

                 }
     
   return tasks ; 
   } 
 //==============================================================================
   //this method to inseryt idle task in this list 
   
   public static List<Task> preeptive_Orgnizer(List<Task> tasks)
   {
        List<Task> result = new ArrayList<>() ;  
          
    
        
     for(int i=0; i<tasks.size(); i++)
       { 
             Task tempTask = null ; 
             int end = 0;
        List<Task> tempList = null; 
              // loop to get a list of tasks have the same name 
              for(int k=0; k< (tasks.size()); k++)
               {
                 if(tasks.get(k).getName().contains(tasks.get(i).getName()))
                 {
                  tempList = new ArrayList<>() ; 
                  tempList.add(tasks.get(i));
                 //System.out.println("temp list "+tasks.get(i).getName() );
                 //end = tasks.get(k).getEndTime();
                 }
                }
            
              
              if(tempList!=null )
              { 
                tempTask = tempList.get(0);
                // take the last elment's time 
                 end = tempTask.getEndTime();

                if(!check_Eistance(result,tempTask))
                {                
                  tempTask.setEndTime(end);
                  result.add(tempTask);
                System.out.println(tempTask.getName() + "start  "+tempTask.getStartTime()+"end  "+tempTask.getEndTime());
                } 
              }
              
            
       }

     
   return result ; 
   } 

   // helpful  method to check if task existe in tasks  
  static boolean check_Eistance (List<Task> tasks ,Task task)
           
   {
       boolean status = false ;
      if (tasks.size() > 0)
      {
              // comming loop to check if the task not added before 
                for(int t=0; t<tasks.size(); t++)
                { 
                   if(tasks.get(t).getName().contains(task.getName()))
                   {  
                   status = true ;
                   }
                }
   
      }
      else status = false ;
          
          
      return status;    
   
   }
}