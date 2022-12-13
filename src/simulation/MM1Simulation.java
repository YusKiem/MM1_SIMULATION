/*
  * To contribute to the optimisation of this code, contact!
 */
package simulation;

/**
 *
 * @author yusuf
 */
public class MM1Simulation extends Simulation {

    public MM1Simulation(double λ, double μ) {
        super(λ, μ);//our simulation depends on λ and μ 
    }

    public double getTempsAttenteMoyenTheorique() {
        double tpsMoyen = this.U / (this.μ * (1 - this.U));
        return tpsMoyen;//La durée d’attente moyenne 𝑊𝑞 d’un client: Wq = Lq/λ little formula
    }

    public double getNombreTotalClientsSystemeTheorique() {
        double moyenne = this.U / (1 - this.U);
        return moyenne;//The number of customers in the sys: L = u/(1-u) littel formula
    }

    @Override
    public void simulate(double simLength) {
        double beginTime = System.currentTimeMillis();//returns the current time in milliseconds.
        double time = 0;//The simulation begins at t=0
        Event s1 = new Event(0, expo(this.λ));//At this instant, 
        //the next event s1 is generated, corresponds to a customer arrival = 0;
        this.liste.addEvent(s1);// We add this event to the list of events 
        this.compteur = 0;
        //We then repeat the following procedure until the end of the desired
        //simulation (a simulation duration or a maximum number of customers processed):
        while (time < simLength) {
            this.compteur++;
            Event next_Event = (Event) this.liste.events.get(0);//We read in order the next event of the list
            this.liste.events.remove(0);// we remove it from the list 
            time = next_Event.getTime();//We advance the time to the date of this event
            if (next_Event.getType() == 0) {//if the event is arrival of a customer
                this.nombre_Clients_Presents++;
                this.nombre_Clients_Presents_Total += this.nombre_Clients_Presents;
                this.liste.addEvent(new Event(0, time + expo(this.λ)));//We add this event to the list of events 
                double serviceTime = expo(this.μ);//we calculate the customer's service duration               
                this.q.lesClients.addElement(new Client(time, serviceTime));/*we add the customer in the queue of customers
                with his/her arrival time eg t1 and duree service eg s1*/
                if (this.q.lesClients.size() == 1) {/* if the customer is the only one in the queue, 
                he will necessarily be the one who will be out soon */
                    this.liste.addEvent(new Event(1, time + serviceTime));/** It is therefore necessary to add 
                    an event corresponding to the departure of this customer  in the list of events whose start time is
                     t + duration of service.*/
                }
            } 
            else {//if the event is a departure of a customer
                Client c = (Client) this.q.lesClients.get(0);
                this.q.lesClients.remove(0);//We delete the client from the queue
                this.nombre_Clients++;//We increase the number of clients
                this.nombre_Clients_Presents--;//number of available clients is decreased since the client has left
                this.nombre_Clients_Presents_Total += this.nombre_Clients_Presents;
                this.compteurTempsService += time - c.getArrivalTime();/*calculation of service time = current time(depart) - arrival time*/
                this.sommeCarreTempsService += (time - c.getArrivalTime()) * (time - c.getArrivalTime());//this means for the variance
                this.compteurTempsAttente += time - c.getArrivalTime() - c.getServiceTime();
                this.sommeCarreTempsAttente += (time - c.getArrivalTime() - c.getServiceTime()) * (time - c.getArrivalTime() - c.getServiceTime());
                if /*(this.q.lesClients.size() > 0)*/(!this.q.lesClients.isEmpty()) {//we check if there is a client in the queue 
                    Client client = (Client) this.q.lesClients.get(0);//we pick the first client from our vector (first come first serve)
                    double serviceTime = client.getServiceTime();//we get the service time for the client
                    this.liste.addEvent(new Event(1, time + serviceTime));//We add this event to the list of events
                }
            }
        }
        //Variance indicates The average degree to which each point differs from the mean
        //Variance means The degree to which returns vary or change over time
        System.out.println("\n=============== Report For The MM1 SIMULATION =================");
        System.out.println("Begin Time is: "+beginTime * 0.001+" seconds");
        System.out.println("End Time is : " + System.currentTimeMillis() * 0.001 + " secondes");
        System.out.println("Calculations Duration: " + (System.currentTimeMillis() - beginTime) * 0.001 + " secondes");
        System.out.println("Average Waiting Time(Theorique) : " + this.getTempsAttenteMoyenTheorique());
        System.out.println("Average Waiting Time (Pratique) : " + this.getTempsAttenteMoyenPratique());
        System.out.println("Variance of The Waiting Time: " + this.getVarianceTempsAttente());
        System.out.println("Average Service Time: " + this.getTempsServiceMoyen());
        System.out.println("Variance of Service Time : " + this.getVarianceTempsService());
        System.out.println("Number of Customers served During the whole simulation: " + this.getNombreTotalClients());
        System.out.println("Average Number of customers in the system(Theorique) : " + this.getNombreTotalClientsSystemeTheorique());// L = u/(1-u)
        System.out.println("Average Number of customers in the system(Pratique) : " + this.getNombreTotalClientsSystemePratique());
    }
}
