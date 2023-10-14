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
import java.util.Objects;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signin.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserRepository userRepositoryJDBC = new UserRepositoryJDBC();
        Optional<User> optionalUser = userRepositoryJDBC.findByName(username);
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getPassword().equals(password)) {
                request.getSession(true).setAttribute("userId", optionalUser.get().getId());
                response.sendRedirect("profile");
            } else{
                response.sendRedirect("sign-in");
            }
        } else {
            response.sendRedirect("sign-in");
        }
    }
}