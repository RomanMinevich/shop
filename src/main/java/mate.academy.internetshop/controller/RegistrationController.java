package mate.academy.internetshop.controller;

import static java.lang.String.format;
import static mate.academy.internetshop.util.HashUtil.hashPassword;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    private static final Logger log = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = new User();
        user.setName(format("%s %s",
                request.getParameter("First name"), request.getParameter("Last name"))
                .trim());
        user.setAddress(request.getParameter("Shipping address"));
        user.setEmail(request.getParameter("Email"));
        user.setPhoneNumber(request.getParameter("Phone number"));
        user.setPassword(hashPassword(
                user.getSalt(), request.getParameter("Password")));
        userService.create(user);
        log.info("User registered");
        bucketService.create(new Bucket(user.getId()));
        log.info("Bucket created");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
