package ru.itis.servletsauth.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.servletsauth.dto.User;

import ru.itis.servletsauth.repositories.UserRepository;

import ru.itis.servletsauth.repositories.UserRepositoryJDBC;

import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = User.builder()
                .name(username)
                .password(password)
                .build();

        UserRepository userRepositoryJDBC = new UserRepositoryJDBC();
        if (userRepositoryJDBC.findByName(username).isPresent()) {
            throw new RuntimeException();
        } else {
            userRepositoryJDBC.save(user);
            request.getSession(true).setAttribute("userId", user.getId());
            response.sendRedirect("profile");
        }


    }
}
