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

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;

    private static final Long TEMP_BUCKET_ID = 0L;
    private static final Long TEMP_USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("items", bucketService.get(0L).getItems());
        request.getRequestDispatcher("WEB-INF/views/bucket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buttonId = request.getParameter("-");
        String bigButton = request.getParameter("Complete order");
        if (buttonId != null) {
            bucketService.removeItem(TEMP_BUCKET_ID, Long.valueOf(buttonId));
            System.out.println("Item removed from bucket");
            response.sendRedirect(request.getContextPath() + "/bucket");
        }
        if (bigButton != null) {
            if (Integer.parseInt(bigButton) > 0) {
                Order order = orderService.completeOrder(
                        bucketService.getAllItems(TEMP_BUCKET_ID), TEMP_USER_ID);
                orderService.create(order);
                System.out.println("Order completed");
                response.sendRedirect(request.getContextPath() + "/orders");
            } else {
                System.out.println("Bucket is empty");
                response.sendRedirect(request.getContextPath() + "/bucket");
            }
        }
    }
}
