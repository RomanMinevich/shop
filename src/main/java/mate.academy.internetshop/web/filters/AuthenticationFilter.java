package mate.academy.internetshop.web.filters;

import static java.util.stream.Stream.of;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebFilter("/servlet/*")
public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest filterRequest, ServletResponse filterResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)filterRequest;
        HttpServletResponse response = (HttpServletResponse)filterResponse;
        Optional<User> user = of(request.getCookies())
                .filter(cookie -> cookie.getName().equals("MATE"))
                .map(Cookie::getValue)
                .map(value -> userService.getByToken(value))
                .findAny()
                .get();
        if (user.isPresent()) {
            log.info("User " + user.get().getId() + " was authenticated");
            chain.doFilter(filterRequest,filterResponse);
        } else {
            log.info("User was not authenticated");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
