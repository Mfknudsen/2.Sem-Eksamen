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
    public PlannerMapper(Database database)
    {
        this.database = database;
    }

    public List<material> listOfMaterials() throws UserException {

        List<material> materialsList = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM `fog`.`materials`";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("material_id");
                    String name = rs.getString("name");
                    float pricePerUnit = rs.getFloat("pricePerUnit");
                    int lengthSQL = rs.getInt("length");
                    String description = rs.getString("description");
                    materialsList.add(new material(id, name, pricePerUnit, lengthSQL, description));
                }
                return materialsList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException | UserException ex)
        {
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

    private void CalculateStolper()
    {
//        11 i databasen er en stolpe

    }

    private void CalculateRem()
    {
//        1, 2, 3 og 4 er Rem

    }

    private void CalculateSpær()
    {
//        8, 9 og 10 er spær

    }

    public List<material> Calculate(float length, float width) throws UserException {
        List<material> list = listOfMaterials();
        List<material> materials = new ArrayList<>();

        CalculateStolper();
        CalculateRem();
        CalculateSpær();

//        calculate needed materials
//        add to materials list materials needed with quantity set to more than 0

        return materials;
    }
}
