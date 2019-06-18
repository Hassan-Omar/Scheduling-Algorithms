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
      Task task = new Task () ; int end =0; 
        
     for(int i=1; i<tasks.size(); i++)
       { List<Task> temp = new ArrayList<>();
              // loop to get a list of tasks have the same name 
              for(int k=1; k< (tasks.size()-1); k++)
               {
                 if(tasks.get(k).getName().equals("A"))
                 {
                     temp.add(tasks.get(k));
                     task.setFlag(false);
                 }
                }
              // now assiging last end time to the first task 
             end   = temp.get(temp.size()-1).getEndTime();
              
              task= temp.get(0);
              task.setEndTime(end);
              
              // add to list 
              if(!task.getFlag())
              {
                  tasks.add(task);
                  task.setFlag(true);
               //   System.out.println(task.getName() + " "+task.getStartTime()+" & "+task.getEndTime());
                  
              }
            
       }

     System.out.println(result.size());
   return result ; 
   } 

}