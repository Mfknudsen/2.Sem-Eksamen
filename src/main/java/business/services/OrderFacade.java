package business.services;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.orderMapper;

import java.util.List;

public class OrderFacade
{
    orderMapper orderMapper;

    public OrderFacade(Database database)
    {
        orderMapper = new orderMapper(database);
    }

    public int add(int user_id, float length, float width) throws UserException {
        return orderMapper.add(user_id, length, width);
    }

    public List<Order> listOfOrders() throws UserException {
        return orderMapper.showList();
    }

}
