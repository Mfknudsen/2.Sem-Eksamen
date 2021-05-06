package business.persistence;

import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;

public class InquiryMapper
{
    private Database database;

    public InquiryMapper(Database database)
    {
        this.database = database;
    }

    public int add(int user_id, float length, float width) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO inquiry (`user_id`, `lengthCM`, `widthCM`) VALUES (?,?,?)";

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
}
