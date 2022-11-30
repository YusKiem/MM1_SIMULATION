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
public class Client {

    protected double tempsArrivee; //c'est un instant
    protected double dureeService; //c'est une durée

    public Client(double arrivalTime, double serviceTime) {
        this.tempsArrivee = arrivalTime;
        this.dureeService = serviceTime;
    }
// si les deux attributs sont declares protected ou private,
// il faut des méthods publiques d’accès à la valeur de ces attributs

    public double getArrivalTime() {
        return this.tempsArrivee;
    }

    public double getServiceTime() {
        return this.dureeService;
    }
}
