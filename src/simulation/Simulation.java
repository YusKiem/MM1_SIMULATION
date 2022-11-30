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
public abstract class Simulation {

    protected double λ;
    protected double μ;
    protected double U;
    protected ListeEvents liste;
    protected Queue q;
    protected double t;
    protected double compteurTempsAttente;
    protected double sommeCarreTempsAttente;
    protected double compteurTempsService;
    protected double sommeCarreTempsService;
    protected int compteur;
    protected int nombre_Clients;
    protected double nombre_Clients_Presents;
    protected double nombre_Clients_Presents_Total;

    public Simulation(double λ, double μ) {
        this.λ = λ;
        this.μ = μ;
        this.U = this.λ / this.μ;
        this.q = new Queue();
        this.liste = new ListeEvents();
        this.compteurTempsAttente = 0;
        this.compteurTempsService = 0;
        this.sommeCarreTempsAttente = 0;
        this.sommeCarreTempsService = 0;
        this.compteur = 0;
        this.nombre_Clients = 0;
        this.nombre_Clients_Presents = 0;
        this.nombre_Clients_Presents_Total = 0;
    }

    public double expo(double taux) {
        return -Math.log(Math.random()) / taux;
    }

    public double getTempsAttenteMoyenPratique() {
        return this.compteurTempsAttente / this.nombre_Clients;
    }

    public double getTempsServiceMoyen() {
        return this.compteurTempsService / this.nombre_Clients;
    }

    public double getVarianceTempsAttente() {
        double varianceAttente = (this.sommeCarreTempsAttente / this.nombre_Clients) - (this.compteurTempsAttente / this.nombre_Clients) * (this.compteurTempsAttente / this.nombre_Clients);
        return varianceAttente;
    }

    public double getVarianceTempsService() {
        double varianceService = (this.sommeCarreTempsService / this.nombre_Clients) - (this.compteurTempsService / this.nombre_Clients) * (this.compteurTempsService / this.nombre_Clients);
        return varianceService;
    }

    public int getNombreTotalClients() {
        return this.nombre_Clients;
    }

    public double getNombreTotalClientsSystemePratique() {
        return this.nombre_Clients_Presents_Total / this.compteur;
    }

    public abstract void simulate(double simLength);

}
