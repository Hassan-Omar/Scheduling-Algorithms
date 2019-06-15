package task.schedular;

import java.util.ArrayList;
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
        System.out.println(q);
    }
    

  //----------------------------------------------------------------------- 
   public List<Task>  drive (List<Task> tasks)
   {
      
       // listing to lists one based on aeeival time ,second based on burst time
       List<Task> tasks_Arri = UtileMethods.sort_onArrival(tasks) ; // this just to know which we will use to start
       List<Task> queu = new ArrayList<>(); 
       List<Task> result_list = new ArrayList<>() ;
       
       // this comming loop to 
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
       
       }
       
          // now we need to set start and end time based on RR
          // |_A_|_C_|_A_|_B_|_D_|_A_|_C_|_D_|_A_|_C_|_A_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int i=0;i<sum_Burst; i+=q)
          {
          if(i==0)
              {
                // first task is the first arrived
                // and it's Start Time = arrival time 
                 tasks_Arri.get(0).setStartTime(tasks_Arri.get(0).getArrivalTime());
                 result_list.add(tasks_Arri.get(0)) ;
                 
                 
              }
              else 
              { 
                 // now we need to move queu(0) to the last
                 for (int x=0; x<tasks.size(); x++)
                 {
                     if(tasks_Arri.get(x).getArrivalTime() < i)
                     {
                     // this means it's delevird
                     queu.add(tasks_Arri.get(x));
                     }
                 }
                  result_list.add(queu.get(0));
                  
              } 
          
          // remain time = burst - q
            queu.get(0).setRemainTime( result_list.get(i).getBurstTime()-q);
          
          // check if the task @ end
          if(queu.get(0).getRemainTime()==0)
          queu.remove(queu.get(0));
          // end = start + q
            result_list.get(i).setEndTime( result_list.get(i).getStartTime()+q );

          // roatate the queue
          queu.add(queu.get(0));
          queu.remove(0);
         
          System.out.println("end "+  result_list.get(i).getEndTime() +"start"+  result_list.get(i).getStartTime());

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
