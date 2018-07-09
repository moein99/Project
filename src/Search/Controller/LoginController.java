package Search.Controller;

import Search.Model.UserMangaer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet{
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userName = request.getParameter("user");
            String password = request.getParameter("pass");
            UserMangaer userManager = new UserMangaer();
            if (userManager.login(userName, password)) {
                request.getSession().setAttribute("login", userName);
                response.sendRedirect("index.jsp");
            }
            else{
                response.sendRedirect("login.jsp");
            }
        }
}
