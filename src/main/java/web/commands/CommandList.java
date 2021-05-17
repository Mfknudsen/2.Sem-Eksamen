package web.commands;

import business.entities.material;
import business.exceptions.UserException;
import business.services.PlannaerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandList extends CommandProtectedPage
{
    public CommandList(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        float length = Float.parseFloat(request.getParameter("length"));
        float width = Float.parseFloat(request.getParameter("width"));

        PlannaerFacade plannaerFacade = new PlannaerFacade(database);

        List<material> listOfMaterials = plannaerFacade.MakeList(length, width);
        request.setAttribute("tableItems", listOfMaterials);
        return pageToShow;
    }
}
