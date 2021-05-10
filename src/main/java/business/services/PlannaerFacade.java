package business.services;

import business.entities.Styklist;
import business.persistence.Planner;

public class PlannaerFacade {
    Planner planner;

    public PlannaerFacade() {
        this.planner = new Planner();
    }

    public void MakeList(float length, float width){
        Styklist list = planner.Calculate(length, width);
        System.out.println((int)list.price);
    }
}
