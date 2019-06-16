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
       
       // this comming loop to  
       int sum_Burst = 0 ;
       for(int j=0; j<tasks.size(); j++)
       {
        sum_Burst +=tasks.get(j).getBurstTime();
       
       }  
       
        tasks = UtileMethods.sort_onArrival(tasks);
       
          int i =0 ;
       
          // now we need to set start and end time based on SRF
          // |___A___|__C__|_A_|_B_|____D____|_C_| 
          // start of TASK(i+1) = end of  TASK(i)  
          for(int index=0;index<sum_Burst;index++)
          {    
              tasks.get(i).setRemainTime(tasks.get(i).getBurstTime());
              
              
            System.out.println("index "+index + " name "+tasks.get(i).getName()+ " arra "+tasks.get(i).getArrivalTime());
              if(tasks.get(i).getArrivalTime()==index)
              {
                System.out.println("index "+index + " name "+tasks.get(i).getName());
                tasks_Arri.add(tasks.get(i));
                UtileMethods.sort_onRem(tasks_Arri).get(0).setStartTime(index);
               // this givs
               result_list.add(UtileMethods.sort_onRem(tasks_Arri).get(0));
             
               if(result_list.get(i).getRemainTime()!=0)
               {
               tasks_Arri.add(result_list.get(i)) ;
               }
               else tasks_Arri.remove(result_list.get(i));
               
               i++;
              }
        //System.out.println("end "+  result_list.get(i).getEndTime() +"start"+  result_list.get(i).getStartTime());
         
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
