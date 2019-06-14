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
public abstract class Algorithm {
    String name ; 
    float awt , art ,att , ufactor , throughput , prop ; 

    public Algorithm(String name, float awt, float art, float att, float ufactor, float throughput, float prop) {
        this.name = name;
        this.awt = awt;
        this.art = art;
        this.att = att;
        this.ufactor = ufactor;
        this.throughput = throughput;
        this.prop = prop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAwt() {
        return awt;
    }

    public void setAwt(float awt) {
        this.awt = awt;
    }

    public float getArt() {
        return art;
    }

    public void setArt(float art) {
        this.art = art;
    }

    public float getAtt() {
        return att;
    }

    public void setAtt(float att) {
        this.att = att;
    }

    public float getUfactor() {
        return ufactor;
    }

    public void setUfactor(float ufactor) {
        this.ufactor = ufactor;
    }

    public float getThroughput() {
        return throughput;
    }

    public void setThroughput(float throughput) {
        this.throughput = throughput;
    }

    public float getProp() {
        return prop;
    }

    public void setProp(float prop) {
        this.prop = prop;
    }
    
 public abstract float ufactor_Calculation ();
 public abstract float throughput_Calculation ();
 public abstract float prop_Calculation ();
 public abstract float awt_Calculation ();
 public abstract float art_Calculation ();
 public abstract float att_Calculation ();
    
}
