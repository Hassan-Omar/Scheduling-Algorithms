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
              try{ 
              tasks.get(i).setRemainTime(tasks.get(i).getBurstTime());
          
              if(i==0)
              {
                  // first task 
               result_list.add(UtileMethods.sort_onArrival(tasks).get(0));
               result_list.get(0).setStartTime( result_list.get(0).getArrivalTime() );
               result_list.get(0).setFlag(true) ;
              }
              
               
               
              if(tasks.get(i).getArrivalTime()==index)
              { 
                tasks_Arri.add(tasks.get(i));
                
               UtileMethods.sort_onRem(tasks_Arri).get(0).setStartTime(index);
               result_list.add(UtileMethods.sort_onRem(tasks_Arri).get(0));
               
               
               if(result_list.get(i).getRemainTime()!=0 && !result_list.get(i).getFlag())
               {
               tasks_Arri.add(result_list.get(i)) ;
               System.out.println("not removed");
               result_list.get(i).setFlag(true) ;
               }
               
              
               System.out.println("a7777777777aaaaaaaa"+result_list.get(i).getRemainTime());
               if(tasks_Arri!=null)
               i++;}
              
             
              
              }catch(Exception e)
              {
              }
              
              result_list.get(i).setEndTime(result_list.get(i).getStartTime() + result_list.get(i).getEndTime()+1);
              result_list.get(i).setRemainTime(result_list.get(i).getRemainTime()-1);
                System.out.println("index "+index +"name "+result_list.get(i).getName()+" "+result_list.get(i).getRemainTime()+" h "+ result_list.get(i).getEndTime());
         if(  result_list.get(i).getRemainTime() ==0)
                {
                   tasks_Arri.remove(result_list.get(i));
                              System.out.println("removed");

               }
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
