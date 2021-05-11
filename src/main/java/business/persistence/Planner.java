package business.persistence;

import business.entities.Styklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Planner {
    //Length and Width in meters
    public Styklist Calculate(float length, float width, boolean shed, float sLength, float sWidth) {
        Styklist result = new Styklist();
        //https://www.johannesfog.dk/byggecenter/produkter/2CARPORT_CAR01/
        //First version based on average price of m2 of referred product.
        float avgPrice = 344.9f;
        float m2 = length * width;

        result.setPrice(m2 * avgPrice);
        System.out.println(width % 3.6f);
        result.totalCounts.put("25x200x360", (int) (Math.floor(width / 3.6f) + 1) * 2);
        result.totalCounts.put("25x200x540", (int) (Math.floor(length / 5.4f) + 1) * 2);

        result.totalCounts.put("25x125x360", (int) (Math.floor(width / 3.6f)));
        result.totalCounts.put("25x125x540", (int) (Math.floor(length / 5.4f) + 1));


        result.totalCounts.put("45x195x600", (int) (Math.floor(length / 0.55f) + 1));

        if (shed && (sLength != 0 && sWidth != 0)) {
            result.totalCounts.put("38x73", 1);

            result.totalCounts.put("45x95x270", (int)Math.floor(sWidth / 0.4f));
            result.totalCounts.put("45x95x240", (int)Math.floor(sLength / 0.525f));

            result.totalCounts.put("45x195x480", 1);

            result.totalCounts.put("19x100x210", (int) ((sLength * sWidth) * 20));
        }

        for (String key: result.totalCounts.keySet()) {
            System.out.println(key + " - " + result.totalCounts.get(key));
        }
        return result;
    }
}
