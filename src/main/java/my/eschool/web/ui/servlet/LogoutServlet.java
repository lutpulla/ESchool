package my.eschool.web.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.eschool.web.ui.controller.AuthController;

/**
 *
 * @author l.avakriyev
 */
public class LogoutServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        AuthController authController = (AuthController) session.getAttribute("authController");
        response.sendRedirect(request.getContextPath() + "/login.htm");
        if (authController != null && authController.isAuthenticate()) {
            authController.exit(session);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "LogoutServlet";
    }
    // </editor-fold>
}
