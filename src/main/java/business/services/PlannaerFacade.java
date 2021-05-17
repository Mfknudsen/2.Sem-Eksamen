package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.PlannerMapper;

import java.util.List;

public class PlannaerFacade {
    PlannerMapper plannerMapper;

    public PlannaerFacade(Database database) {
        this.plannerMapper = new PlannerMapper(database);
    }

    public List<Material> MakeList(float length, float width) throws UserException {
//        calculate materials needed from "list"
        List<Material> newList = plannerMapper.Calculate(length, width);
        return newList;
    }
}
