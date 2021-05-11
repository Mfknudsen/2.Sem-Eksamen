package business.services;

import business.entities.Styklist;
import business.persistence.Planner;

public class PlannaerFacade {
    Planner planner;

    public PlannaerFacade() {
        this.planner = new Planner();
    }

    public Styklist MakeList(float length, float width){
        Styklist list = planner.Calculate(length, width, false,0,0);
        return list;
    }
}
