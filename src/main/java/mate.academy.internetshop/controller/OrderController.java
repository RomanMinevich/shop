package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebServlet("/servlet/orders")
public class OrderController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;

    private static final Logger log = Logger.getLogger(OrderController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long)request.getSession(true).getAttribute("userId");
        request.setAttribute("orders", userService.getOrders(userId));
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String orderId = request.getParameter("Remove");
        if (orderId != null) {
            orderService.deleteOrder(Long.valueOf(orderId));
            log.info("Order deleted");
            response.sendRedirect(request.getContextPath() + "/servlet/orders");
        }
    }
}
