package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebFilter("/servlet/*")
public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> urls = new HashMap<>();
    private static final Logger log = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.put("/servlet/bucket", USER);
        urls.put("/servlet/orders", USER);
        urls.put("/servlet/users", ADMIN);
    }

    @Override
    public void doFilter(
            ServletRequest filterRequest, ServletResponse filterResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)filterRequest;
        HttpServletResponse response = (HttpServletResponse)filterResponse;
        Long userId = (Long)request.getSession(true).getAttribute("userId");
        if (userId == null) {
            log.info("User was not authenticated");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            if (validate(userId, request)) {
                log.info("User " + userId + " was authorized");
                chain.doFilter(filterRequest, filterResponse);
            } else {
                log.info("User " + userId + " was not authorized");
                request.getRequestDispatcher("/WEB-INF/views/accessDenial.jsp")
                        .forward(request, response);
            }
        }
    }

    private boolean validate(Long userId, HttpServletRequest request) {
        Role.RoleName validRoleName = urls.get(
                request.getRequestURI().replace(request.getContextPath(), ""));
        return validRoleName == null
                || userService.getUserRoles(userId)
                .stream()
                .anyMatch(role -> role.getName().equals(validRoleName));
    }

    @Override
    public void destroy() {

    }
}
