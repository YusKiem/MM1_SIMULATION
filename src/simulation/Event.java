/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author yusuf
 */
public class Event {

    protected int type; //0 correspond à une arrive, 1 à un départ
    protected double instant; //instant où un événement s’est produit

    public Event(int type, double time) {
        this.type = type;
        this.instant = time;
    }

    public int getType() {
        return type;
    }

    public double getTime() {
        return instant;
    }
}
