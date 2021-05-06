package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.InquiryMapper;
import business.persistence.UserMapper;

public class InquiryFacade
{
    InquiryMapper inquiryMapper;

    public InquiryFacade(Database database)
    {
        inquiryMapper = new InquiryMapper(database);
    }

    public int add(int user_id, float length, float width) throws UserException {
        return inquiryMapper.add(user_id, length, width);
    }

}
