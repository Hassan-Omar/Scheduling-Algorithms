/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.schedular;

/**
 *
 * @author h.omar
 */
public class Task {
    private int num ,burstTime,arrivalTime , periorty ; 

    public Task(int num, int burstTime, int arrivalTime, int periorty) {
        this.num = num;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.periorty = periorty;
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

    public int getPeriorty() {
        return periorty;
    }

    public void setPeriorty(int periorty) {
        this.periorty = periorty;
    }
}
