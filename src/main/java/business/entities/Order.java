package business.entities;

public class Order
{
    private int id;
    private String name;
    private float length,width;

    public Order(int id, String name, float length, float width)
    {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }
}
