package business.persistence;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class orderMapper
{
    private Database database;

    public orderMapper(Database database)
    {
        this.database = database;
    }

    public int add(int user_id, float length, float width) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO `order` (`user_id`, `lengthCM`, `widthCM`) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, user_id);
                ps.setFloat(2, length);
                ps.setFloat(3, width);
                int update = ps.executeUpdate();
                if(update == 0)
                {
                    throw new UserException("Could not find user with matching id");
                }
                return update;

            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Order> showList() throws UserException
    {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            String sql = "SELECT `order`.`order_id`, `user`.`name`, `order`.`lengthCM`, `order`.`widthCM` " +
                    "FROM `order` " +
                    "INNER JOIN user ON `order`.`user_id` = `user`.`user_id`";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("order_id");
                    String name = rs.getString("name");
                    float length = rs.getFloat("lengthCM");
                    float width = rs.getFloat("widthCM");
                    orderList.add(new Order(id, name, length, width));
                }
                return orderList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }
}
