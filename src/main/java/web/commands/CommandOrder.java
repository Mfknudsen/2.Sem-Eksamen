package web.commands;

import business.entities.Styklist;
import business.exceptions.UserException;
import business.services.PlannaerFacade;
import business.services.orderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandOrder extends CommandUnprotectedPage
{
    orderFacade orderFacade = new orderFacade(database);

    public CommandOrder(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        float length = Float.parseFloat(request.getParameter("length"));
        float width = Float.parseFloat(request.getParameter("width"));


        int update = orderFacade.add(1, length, width);
        if (!(update > 0))
        {
            request.setAttribute("error", "Could not add inquiry. Possibly because user id does not exist");
        }

        request.setAttribute("update", "Din forspørgsel er blevet sendt!");

        System.out.println("Number of updated rows: " + update);

        return pageToShow;
    }
}


