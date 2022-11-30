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
        int insertIndex = 0; //sert à repérer l’endroit où l’événement newEvent doit être insérer 
//on compare l’instant de newEvent avec l’instant des autres événements                déjà dans la liste
        while (insertIndex < events.size()) {
            Event e = (Event) events.elementAt(insertIndex); //on extrait le insertIndex ème événement
            if (e.getTime() > newEvent.getTime()) {
                break; //si l’instant de newEvent est le plus près,
            }
//on sort de la boucle
            insertIndex++; //sinon on incrémente insertIndex, et on reboucle
        }
        events.insertElementAt(newEvent, insertIndex); //on insert l’événement newEvent au bon endroit
    }
}
