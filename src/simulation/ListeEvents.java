/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.Vector;

/**
 *
 * @author Samy
 */
public class ListeEvents {
    // cette classe modélise la liste des événements futurs 

    Vector events;

    public ListeEvents() {
        events = new Vector();
    }

    public void addEvent(Event newEvent) {
        int insertIndex = 0; //is used to locate the place where the newEvent event must be inserted
        //we compare the time of newEvent with the time of the other events already in the list
        while (insertIndex < events.size()) {
            Event e = (Event) events.elementAt(insertIndex); //we extract the insertIndex th event
            if (e.getTime() > newEvent.getTime()) {
                break; //if the instant of newEvent is closest
            }
            //on sort de la boucle
            insertIndex++; //otherwise we increment insertIndex, and we loop
        }
        events.insertElementAt(newEvent, insertIndex); //on insert l’événement newEvent au bon endroit
    }
}
