package ua.nure.cs.savenkov.usermanagement.web;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import ua.nure.cs.savenkov.usermanagement.User;
import ua.nure.cs.savenkov.usermanagement.db.DaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.DataBaseException;

public class AddServlet extends EditServlet {

    private static final String ADD_JSP = "/add.jsp";

	@Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_JSP).forward(req, resp);
    }

    @Override
    protected User getUser(HttpServletRequest req) throws ParseException, ValidationException {
        return super.getUser(req);
    }

    @Override
    protected void processUser(User user) throws DataBaseException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}
