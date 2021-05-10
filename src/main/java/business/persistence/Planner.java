package business.persistence;

import business.entities.Styklist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Planner {
    //Length and Width in meters
    public Styklist Calculate(float length, float width, boolean shed, float sLength, float sWidth){
        Styklist result = new Styklist();
        //https://www.johannesfog.dk/byggecenter/produkter/2CARPORT_CAR01/
        //First version based on average price of m2 of referred product.
        float avgPrice = 344.9f;
        float m2 = length * width;

        result.setPrice(m2 * avgPrice);

        if(shed){

        }

        return result;
    }
}
