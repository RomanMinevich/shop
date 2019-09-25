package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.Logger;

@WebServlet("/servlet/bucket")
public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;

    private Long userId;
    private Bucket bucket;

    private static final Logger log = Logger.getLogger(BucketController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userId = (Long)request.getSession(true).getAttribute("userId");
        bucket = bucketService.getByUserId(userId);
        request.setAttribute("items", bucket.getItems());
        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemsSize = request.getParameter("itemsSize");
        String itemId = request.getParameter("itemId");
        if (itemsSize != null) {
            if (Integer.parseInt(itemsSize) > 0) {
                orderService.completeOrder(bucketService.addItemsToOrder(bucket.getId()), userId);
                log.info("Order completed");
                response.sendRedirect(request.getContextPath() + "/servlet/orders");
            } else {
                request.setAttribute("errorMessage", "Bucket is empty");
                request.getRequestDispatcher("/WEB-INF/views/bucket.jsp")
                        .forward(request, response);
            }
        }
        if (itemId != null) {
            bucketService.removeItem(bucket.getId(), Long.valueOf(itemId));
            log.info("Item removed from bucket");
            response.sendRedirect(request.getContextPath() + "/servlet/bucket");
        }
    }
}
