package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.Logger;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;

    private static final Long TEMP_BUCKET_ID = 0L;
    private static final Long TEMP_USER_ID = 0L;

    private static final Logger log = Logger.getLogger(BucketController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("items", bucketService.get(0L).getItems());
        request.getRequestDispatcher("WEB-INF/views/bucket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addItemsToOrder = request.getParameter("Complete order");
        String removeItemFromBucket = request.getParameter("Remove");
        if (addItemsToOrder != null && Integer.parseInt(addItemsToOrder) > 0) {
            Order order = orderService.completeOrder(
                    bucketService.addAllItemsToOrder(TEMP_BUCKET_ID), TEMP_USER_ID);
            orderService.create(order);
            log.info("Order completed");
            response.sendRedirect(request.getContextPath() + "/orders");
        } else {
            if (removeItemFromBucket != null) {
                bucketService.removeItem(TEMP_BUCKET_ID, Long.valueOf(removeItemFromBucket));
                log.info("Item removed from bucket");
            }
            response.sendRedirect(request.getContextPath() + "/bucket");
        }
    }
}
