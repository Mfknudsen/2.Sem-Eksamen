package business.persistence;

import business.entities.Styklist;

public class Planner {
    public Styklist Calculate(float length, float width){
        Styklist result = new Styklist();
        //https://www.johannesfog.dk/byggecenter/produkter/2CARPORT_CAR01/
        //First version based on average price of m2 of referred product.
        float avgPrice = 344.9f;
        float m2 = length * width;

        result.price = m2 * avgPrice;

        return result;
    }
}
