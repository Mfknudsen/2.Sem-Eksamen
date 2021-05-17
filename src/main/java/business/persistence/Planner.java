package business.persistence;

import business.entities.Styklist;

public class Planner {
    Database database;

    public Planner(Database database) {
        this.database = database;
    }

    //Length and Width in meters
    public Styklist Calculate(float length, float width, boolean shed, float sLength, float sWidth) {
        Styklist result = new Styklist();
        //https://www.johannesfog.dk/byggecenter/produkter/2CARPORT_CAR01/
        //First version based on average price per m2 of referred product.
        length /= 100;
        width /= 100;
        float avgPrice = 344.9f;
        float m2 = length * width;

        result.setPrice(m2 * avgPrice);
        result.totalCounts.put("25x200x360", (int) (Math.floor(width / 3.6f) + 1) * 2);
        result.totalCounts.put("25x200x540", (int) (Math.floor(length / 5.4f) + 1) * 2);

        result.totalCounts.put("25x125x360", (int) (Math.floor(width / 3.6f)));
        result.totalCounts.put("25x125x540", (int) (Math.floor(length / 5.4f) + 1));

        result.totalCounts.put("45x195x600", (int) (length / 6.0f) + 1);
        result.totalCounts.put("45x195x600r", (int) (Math.floor(length / 0.55f) + 1));

        result.totalCounts.put("19x100x540", (int) (Math.floor(length / 5.4f) + 1) * 2);
        result.totalCounts.put("19x100x360", (int) (Math.floor(width / 3.6f) + 1));

        result.totalCounts.put("Plast600", (int) (m2 / 7.8f));
        result.totalCounts.put("Plast360", (int) (m2 / 7.8f));

        result.totalCounts.put("", 3);

        if (shed && sLength > 0 && sWidth > 0) {
            result.totalCounts.put("38x73", 1);

            result.totalCounts.put("45x95x270", (int) Math.floor(sWidth / 0.4f));
            result.totalCounts.put("45x95x240", (int) Math.floor(sLength / 0.525f));

            result.totalCounts.put("45x195x480", 1);

            result.totalCounts.put("19x100x210", (int) ((sLength * sWidth) * 20 + 1));
        }

        for (String key : result.totalCounts.keySet()) {
            System.out.println(key + " - " + result.totalCounts.get(key));
        }
        return result;
    }
}
