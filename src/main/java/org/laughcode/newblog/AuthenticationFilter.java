package org.laughcode.newblog;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.laughcode.newblog.controllers.UserController;
import org.laughcode.newblog.data.UserRepository;
import org.laughcode.newblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    private static final List<String> approvedList = Arrays.asList("/login", "/add", "/logout", "/css");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isApproved(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }
            HttpSession session = request.getSession();
            User user = userController.getUserFromSession(session);

            // The user is logged in
            if (user != null) {
                return true;
            }

            // The user is NOT logged in
            response.sendRedirect("/login");
            return false;
        }

    private static boolean isApproved(String path) {
        for (String pathRoot : approvedList) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

}
