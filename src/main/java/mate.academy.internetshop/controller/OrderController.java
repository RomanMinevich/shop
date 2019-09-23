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

@WebServlet("/orders")
public class OrderController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;

    private static final Long TEMP_USER_ID = 0L;

    private static final Logger log = Logger.getLogger(OrderController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("orders", userService.get(TEMP_USER_ID).getOrders());
        request.getRequestDispatcher("WEB-INF/views/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("Remove");
        if (orderId != null) {
            orderService.deleteOrder(Long.valueOf(orderId));
            log.info("Order deleted");
            response.sendRedirect(request.getContextPath() + "/orders");
        }
    }
}
