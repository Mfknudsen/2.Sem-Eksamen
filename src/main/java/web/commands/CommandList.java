package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.PlannaerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandList extends CommandProtectedPage
{
    PlannaerFacade plannaerFacade;

    public CommandList(String pageToShow, String role) {
        super(pageToShow, role);
        this.plannaerFacade = new PlannaerFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        float length = Float.parseFloat(request.getParameter("length"));
        float width = Float.parseFloat(request.getParameter("width"));

        List<Material> listOfMaterials = plannaerFacade.MakeList(length, width);
        request.setAttribute("tableItems", listOfMaterials);
        request.setAttribute("length", length);
        request.setAttribute("width", width);
        return pageToShow;
    }
}
