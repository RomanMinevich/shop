package mate.academy.internetshop.controller;

import static java.util.stream.Stream.of;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet("/servlet/logout")
public class LogoutController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        log.info(
                "User " + request.getSession(true).getAttribute("userId") + " is logged out");
        of(request.getCookies())
                .filter(cookie -> cookie.getName().equals("MATE"))
                .peek(cookie -> cookie.setMaxAge(0))
                .forEach(response::addCookie);
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
