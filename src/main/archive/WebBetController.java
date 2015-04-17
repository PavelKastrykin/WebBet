package archive;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WebBetController extends HttpServlet{

    public WebBetController(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        FootballMatchDAO dao = new FootballMatchDAO();
        List<FootballMatch> list = dao.viewAllMatches((page - 1)*recordsPerPage, recordsPerPage);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("matchesList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
