package archive;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale extends HttpServlet {

    public ChangeLocale(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter("language");
        session.setAttribute("localeValue", currentLocale);

        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter("hiddenPageID"));

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
