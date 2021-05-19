package business.entities;

public class Material {
    private int id, length;
    private String name, description, category, unit;
    private float pricePerUnit;
    private int quantity = 0;

    public Material(int id, String name, float pricePerUnit, int length, String description, String category, String unit)
    {
       this.id = id;
       this.name = name;
       this.pricePerUnit = pricePerUnit;
       this.length = length;
       this.description = description;
       this.category = category;
       this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }
}
