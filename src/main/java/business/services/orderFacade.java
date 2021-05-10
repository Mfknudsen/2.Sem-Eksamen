package business.services;

import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.orderMapper;

public class orderFacade
{
    orderMapper orderMapper;

    public orderFacade(Database database)
    {
        orderMapper = new orderMapper(database);
    }

    public int add(int user_id, float length, float width) throws UserException {
        return orderMapper.add(user_id, length, width);
    }

}
