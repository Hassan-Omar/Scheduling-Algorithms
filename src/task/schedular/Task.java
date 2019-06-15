package task.schedular;

/**
 *
 * @author h.omar
 */
public class Task {
    private int num ,burstTime,arrivalTime ,startTime ,endTime, deadLine ; 

    public int getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    private int waitTime , totalATime  ,responseTime ;

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    Task() {
     }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getTotalATime() {
        return totalATime;
    }

    public void setTotalATime(int totalATime) {
        this.totalATime = totalATime;
    }

    public Task(int num, int burstTime, int arrivalTime ) {
        this.num = num;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    
}
