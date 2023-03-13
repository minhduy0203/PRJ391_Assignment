/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author MinhDuy
 */
public class LoginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user_raw = req.getParameter("user");
        String pass_raw = req.getParameter("pass");
        UserDBContext dbUser = new UserDBContext();
        User s = dbUser.getByUser(user_raw, pass_raw);
        if (s != null) {
            session.setAttribute("user", s);
            resp.sendRedirect("home");
        } else {
            req.setAttribute("error", "Your user or pass word is not correct.");
            req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
    }
    
}
