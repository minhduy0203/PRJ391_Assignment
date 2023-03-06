/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

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
public abstract class BaseRequiredAuthenticationStudent extends HttpServlet {

    public static boolean isAuthenticated(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u != null && u.getStudent() != null) {
            return true;
        }
        return false;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            doPost(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().print("Access denied");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            doGet(req, resp, (User) req.getSession().getAttribute("user"));
        } else {
            resp.getWriter().print("Access denied");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

}
