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
   {
      
       // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = UtileMethods.sort_onArrival(tasks) ; // this just to know which we will use to start
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
          addArrived(tasks,index);
          if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                  
                // remain time = burst - q
            //   tasks_Arri.get(0).setRemainTime( tasks_Arri.get(0).getBurstTime()-q);
       
                 //queue.add(tasks_Arri.get(0));
                  }
           
            result_list.add(queue.poll()) ;
             if(i!=0)            
            result_list.get(i).setStartTime(index);
            
            // end = start + q
            result_list.get(i).setEndTime( result_list.get(i).getStartTime()+q );
            result_list.get(i).setRemainTime(result_list.get(i).getBurstTime()-q);
            if(result_list.get(i).getRemainTime()!=0) 
            {
            queue.add(result_list.get(i)) ; 
            }
         
        System.out.println("task "+  result_list.get(i).getName() +"start"+  result_list.get(i).getStartTime() +"end "+  result_list.get(i).getEndTime() );
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




 //==============================================================================
   //this method gice the taskeswhich arrived at certain time
   
   public  void addArrived(List<Task> tasks , int index)
   {
    
     for(int x=0; x<tasks.size(); x++)
     {
      // System.out.println("x "+x +" " + index +"flag  "+tasks.get(x).getFlag() + "  ar   "+tasks.get(x).getArrivalTime());
     if(tasks.get(x).getArrivalTime() <=index)   
        { 
        if(!tasks.get(x).getFlag())
        { queue.add(tasks.get(x)) ;
         tasks.get(x).setFlag(true);
                  System.out.println(tasks.get(x).getName());

        } 
      
          } 
     
     } 
     
     
    
   }


}
