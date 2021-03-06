package business.persistence;

import business.entities.Styklist;
import business.entities.Material;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlannerMapper {
    private Database database;

    public PlannerMapper(Database database) {
        this.database = database;
    }

    public List<Material> listOfMaterials() throws UserException {

        List<Material> materialsList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT `materials`.`material_id`, " +
                    "`materials`.`name`, `materials`.`pricePerUnit`, `materials`.`length`, `materials`.`description`, " +
                    "`materials`.`category`, `unit`.`unit` " +
                    "FROM materials INNER JOIN unit on `materials`.`material_id` = `unit`.`material_id`;";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("material_id");
                    String name = rs.getString("name");
                    float pricePerUnit = rs.getFloat("pricePerUnit");
                    int lengthSQL = rs.getInt("length");
                    String description = rs.getString("description");
                    String category = rs.getString("category");
                    String unit = rs.getString("unit");
                    if (category == null)
                    {
                        category = "-1";
                    }
                    materialsList.add(new Material(id, name, pricePerUnit, lengthSQL, description, category, unit));
                }
                return materialsList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    //Length and Width in meters
    public Styklist Calculate(float length, float width, boolean shed, float sLength, float sWidth) {
        Styklist result = new Styklist();
        //https://www.johannesfog.dk/byggecenter/produkter/2CARPORT_CAR01/
        //First version based on average price of m2 of referred product.
        float avgPrice = 344.9f;
        float m2 = length * width;

        result.setPrice(m2 * avgPrice);
        System.out.println(width % 3.6f);
        //
        result.totalCounts.put("25x200x360", (int) (Math.floor(width / 3.6f) + 1) * 2);
        result.totalCounts.put("25x200x540", (int) (Math.floor(length / 5.4f) + 1) * 2);

        result.totalCounts.put("25x125x360", (int) (Math.floor(width / 3.6f)));
        result.totalCounts.put("25x125x540", (int) (Math.floor(length / 5.4f) + 1));

        result.totalCounts.put("45x195x600", (int) (Math.floor(length / 0.55f) + 1));

        result.totalCounts.put("97x97", (int) (length / 3.1f + 1) * 2);

        result.totalCounts.put("19x100x540", (int) (Math.floor(length / 5.4f) + 1) * 2);
        result.totalCounts.put("19x100x360", (int) (Math.floor(width / 3.6f) + 1));

        result.totalCounts.put("Plast600", (int) (m2 / 7.8f));
        result.totalCounts.put("Plast360", (int) (m2 / 7.8f));
        //
        result.totalCounts.put("bSkrue", (int) ((m2 / 7.8f) / 4));
        result.totalCounts.put("hulb??nd", (int) (length / 10 + 1) * 2);

        result.totalCounts.put("uniH", (int) (Math.floor(length / 0.55f) + 1));
        result.totalCounts.put("uniV", (int) (Math.floor(length / 0.55f) + 1));

        result.totalCounts.put("skruPak", (int) (m2 / 100 + 1));
        result.totalCounts.put("beslagS", (int) ((int) (Math.floor(length / 0.55f) + 1) * 2 / 15) + ((int) (length / 10 + 1) * 2) / 3);

        result.totalCounts.put("firkant", (int) (Math.floor(width / 3.6f) + 1) * 2 * 3);
        result.totalCounts.put("br??d", (int) ((Math.floor(width / 3.6f) + 1) * 2 * 3 * 1.5f));
        result.totalCounts.put("70s", (int) (length / 5 + 1));
        result.totalCounts.put("50s", (int) (width / 5 + 1));

        if (shed && (sLength != 0 && sWidth != 0)) {
            //
            result.totalCounts.put("97x97", (int) result.totalCounts.get("97x97") + 5);

            result.totalCounts.put("38x73", 1);

            result.totalCounts.put("45x95x270", (int) Math.floor(sWidth / 0.4f));
            result.totalCounts.put("45x95x240", (int) Math.floor(sLength / 0.525f));

            result.totalCounts.put("45x195x480", 1);

            result.totalCounts.put("19x100x210", (int) ((sLength * sWidth) * 20));
            //

            result.totalCounts.put("stald", 1);
            result.totalCounts.put("h??ngsel", 2);
            result.totalCounts.put("vinkel", 32);

        }

        return result;
    }

    private List<Material> CalculateStolper(float length, List<Material> list)
    {
//        return list<material> af stolpe med quantity sat til mere end 0
        List<Material> stolper = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("stolpe") && i < list.size()) {
            i++;
        }
        Material stolpe = list.get(i);
        stolpe.setQuantity((int) ((length / 301 + 1) * 2));
        stolper.add(stolpe);
        return stolper;
    }

    private List<Material> CalculateRem(List<Material> list)
    {
//        return list<material> af rem med quantity sat til mere end 0
        List<Material> remmer = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("rem") && i < list.size()) {
            i++;
        }
        Material rem = list.get(i);
        rem.setQuantity(2);
        remmer.add(rem);
        return remmer;
    }

    private List<Material> CalculateSp??r(float length, List<Material> list)
    {
//        return list<material> af sp??r med quantity sat til mere end 0
        List<Material> xSp??r = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("sp??r") && i < list.size()) {
            i++;
        }
        Material sp??r = list.get(i);
        sp??r.setQuantity((int) ((length / 55) + 1));
        xSp??r.add(sp??r);
        return xSp??r;
    }

    public List<Material> Calculate(float length, float width) throws UserException
    {
        List<Material> list = listOfMaterials();
        List<Material> materials = new ArrayList<>();

        materials.addAll(CalculateStolper(length, list));
        materials.addAll(CalculateRem(list));
        materials.addAll(CalculateSp??r(width, list));
        for (Material items:materials)
        {
            System.out.println(items.getId() + " " + items.getName() + " " + items.getLength() + " " + items.getQuantity() + items.getDescription());
        }

//        calculate needed materials
//        add to materials list materials needed with quantity set to more than 0

        return materials;
    }
}
