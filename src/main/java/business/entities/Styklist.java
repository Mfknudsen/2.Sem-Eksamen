package business.entities;

import java.util.HashMap;

public class Styklist {
    float price;
    public HashMap<String, Integer> totalCounts = new HashMap<>();

    public Styklist() {
        totalCounts.put("25x200x360", 0);
        totalCounts.put("25x200x540", 0);
        totalCounts.put("25x125x360", 0);
        totalCounts.put("25x125x540", 0);
        totalCounts.put("38x73", 0);
        totalCounts.put("45x95x270", 0);
        totalCounts.put("45x95x240", 0);
        totalCounts.put("45x195x600", 0);
        totalCounts.put("45x195x480", 0);
        totalCounts.put("45x195x600r", 0);
        totalCounts.put("97x97", 0);
        totalCounts.put("19x100x210", 0);
        totalCounts.put("19x100x540", 0);
        totalCounts.put("19x100x360", 0);
        totalCounts.put("Plast600", 0);
        totalCounts.put("Plast360", 0);
        totalCounts.put("bSkrue", 0);
        totalCounts.put("hulbånd", 0);
        totalCounts.put("uniH", 0);
        totalCounts.put("uniV", 0);
        totalCounts.put("skruPak", 0);
        totalCounts.put("beslagS", 0);
        totalCounts.put("bræd", 0);
        totalCounts.put("firkant", 0);
        totalCounts.put("70s", 0);
        totalCounts.put("50s", 0);
        totalCounts.put("stald", 0);
        totalCounts.put("hængsel", 0);
        totalCounts.put("vinkel", 0);
    }

    public HashMap<String, Integer> getTotalCounts() {
        return totalCounts;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
