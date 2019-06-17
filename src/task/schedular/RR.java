package task.schedular;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author h.omar
 */
public class RR extends Algorithm {
    Queue<Task> queue = new LinkedList<>(); 
 int q=1 ; 
    public RR() {
        this.setName("RR");
       // read q from user 
       String s = JOptionPane.showInputDialog("Enter Q");
         q =(int)Float.parseFloat(s) ;
    }
    

  //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {  boolean flag =false ;
      
       // listing to lists one based on aeeival time ,second based on burst time
       Task task1_Arri = UtileMethods.sort_onArrival(tasks).get(0) ; // this just to know which we will use to start
       List<Task> result_list = new ArrayList<>() ;
       List<Task> out = new ArrayList<>() ;

       // this comming loop to 
       int sum_Burst = 0 ;
       
       
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
        tasks.get(j).setRemainTime(tasks.get(j).getBurstTime());
       
       }
                     int i =0 ;

          // now we need to set start and end time based on RR
          // |_A_|_C_|_A_|_B_|_D_|_A_|_C_|_D_|_A_|_C_|_A_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int index=0;index<sum_Burst; index+=q)
          {
          addArrived(tasks,index);
          if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 task1_Arri.setStartTime(task1_Arri.getArrivalTime());
               }
           
            result_list.add(queue.poll()) ;
         
           // System.out.println("1   "+result_list.get(i).getName() + "  "+ result_list.get(i).getStartTime());
            try
            { 
             if(flag)
            {result_list.get(i).setStartTime(result_list.get(i-1).getEndTime());
            flag= false ; 
            }
             else result_list.get(i).setStartTime(index);
             
            
            }
            catch(Exception e)
            {
            }
           //   System.out.println("2   "+result_list.get(i).getName() + "  "+ result_list.get(i).getStartTime());
           
            // update remaining
            result_list.get(i).setRemainTime(result_list.get(i).getRemainTime()-q);
            if( result_list.get(i).getRemainTime()>0)
            {// end = start + q
            result_list.get(i).setEndTime( result_list.get(i).getStartTime()+q );
            }
            else 
            {  result_list.get(i).setEndTime( result_list.get(i).getStartTime()+q+result_list.get(i).getRemainTime());
               flag =true ; 
               
            }   
            System.out.println("task"+result_list.get(i).getName() + " rem "+result_list.get(i).getRemainTime());
            
            
            if(result_list.get(i).getRemainTime()!=0) 
            {  
            queue.add(result_list.get(i)) ; 
            }
                
           dataCopy(result_list.get(i),out);
       
          i++;}
          
         
        
        List<Task> tasks_Setted =   UtileMethods.setParam(out) ;
  
        // start calculation using the parent methods 
       // this.art = this.art_Calculation(tasks_Setted);   
      //  this.att = this.att_Calculation(tasks_Setted);  
      //  this.awt = this.awt_Calculation(tasks_Setted); 
       // this.ufactor = this.ufactor_Calculation(tasks_Setted); 
      //  this.throughput = this.throughput_Calculation(tasks_Setted); 
       // this.prop = this.prop_Calculation(tasks_Setted); 
      
      return tasks_Setted ;
  
   } 




 //==============================================================================
   //this method put the arrived task into queue
   
   public  void addArrived(List<Task> tasks , int index)
   {
    
     for(int x=0; x<tasks.size(); x++)
     {
     if(tasks.get(x).getArrivalTime() <=index)   
        { 
        if(!tasks.get(x).getFlag())
        {  
         queue.add(tasks.get(x)) ;
         tasks.get(x).setFlag(true);
              
        } 
      
          } 
     
     } 
     
     
    
   }


   void dataCopy(Task t , List<Task> o)
   {
    Task temp = new Task() ;
            temp.setArrivalTime(t.getArrivalTime());
            temp.setBurstTime(t.getBurstTime());
            temp.setDeadLine(t.getDeadLine());
            temp.setName(t.getName());
            temp.setStartTime(t.getStartTime());
            temp.setEndTime(t.getEndTime());
            o.add(temp);
   }
   
}
    