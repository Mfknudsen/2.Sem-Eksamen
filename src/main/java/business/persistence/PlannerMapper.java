package business.persistence;

import business.entities.Styklist;
import business.entities.material;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlannerMapper {
    private Database database;

    public PlannerMapper(Database database) {
        this.database = database;
    }

    public List<material> listOfMaterials() throws UserException {

        List<material> materialsList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `fog`.`materials`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("material_id");
                    String name = rs.getString("name");
                    float pricePerUnit = rs.getFloat("pricePerUnit");
                    int lengthSQL = rs.getInt("length");
                    String description = rs.getString("description");
                    String category = rs.getString("category");
                    materialsList.add(new material(id, name, pricePerUnit, lengthSQL, description, category));
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

        result.totalCounts.put("97x97", (int)(length / 3.1f + 1) * 2);

        result.totalCounts.put("19x100x540", (int) (Math.floor(length / 5.4f) + 1) * 2);
        result.totalCounts.put("19x100x360", (int) (Math.floor(width / 3.6f) + 1));

        result.totalCounts.put("Plast600", (int) (m2 / 7.8f));
        result.totalCounts.put("Plast360", (int) (m2 / 7.8f));
        //
        result.totalCounts.put("bSkrue", (int) ((m2 / 7.8f) / 4));
        result.totalCounts.put("hulbånd", (int) (length / 10 + 1) * 2);

        result.totalCounts.put("uniH", (int) (Math.floor(length / 0.55f) + 1));
        result.totalCounts.put("uniV", (int) (Math.floor(length / 0.55f) + 1));

        result.totalCounts.put("skruPak", (int) (m2 / 100 + 1));
        result.totalCounts.put("beslagS", (int) ((int) (Math.floor(length / 0.55f) + 1) * 2 / 15) + ((int) (length / 10 + 1) * 2) / 3);

        result.totalCounts.put("firkant", (int) (Math.floor(width / 3.6f) + 1) * 2 * 3);
        result.totalCounts.put("bræd", (int) ((Math.floor(width / 3.6f) + 1) * 2 * 3 * 1.5f));
        result.totalCounts.put("70s", (int) (length / 5 + 1));
        result.totalCounts.put("50s", (int) (width / 5 + 1));

        if (shed && (sLength != 0 && sWidth != 0)) {
            //
            result.totalCounts.put("97x97", (int)result.totalCounts.get("97x97") + 5);

            result.totalCounts.put("38x73", 1);

            result.totalCounts.put("45x95x270", (int) Math.floor(sWidth / 0.4f));
            result.totalCounts.put("45x95x240", (int) Math.floor(sLength / 0.525f));

            result.totalCounts.put("45x195x480", 1);

            result.totalCounts.put("19x100x210", (int) ((sLength * sWidth) * 20));
            //

            result.totalCounts.put("stald", 1);
            result.totalCounts.put("hængsel", 2);
            result.totalCounts.put("vinkel", 32);

        }

        return result;
    }

    private List<material> CalculateStolper(float length,List<material> list)
    {
//        11 i databasen er en stolpe
        return null;
    }

    private List<material> CalculateRem(List<material> list) {
//        1, 2, 3 og 4 er Rem
        return null;
    }

    private List<material> CalculateSpær(List<material> list) {
//        8, 9 og 10 er spær
        return null;
    }

    public List<material> Calculate(float length, float width) throws UserException {
        List<material> list = listOfMaterials();
        List<material> materials = new ArrayList<>();

        materials.addAll(CalculateStolper(list));
        materials.addAll(CalculateRem(list));
        materials.addAll(CalculateSpær(list));

//        calculate needed materials
//        add to materials list materials needed with quantity set to more than 0

        return materials;
    }
}
