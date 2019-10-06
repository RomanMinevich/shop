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

    private static final Logger log = Logger.getLogger(BucketController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long userId = (Long)request.getSession(true).getAttribute("userId");
        request.setAttribute("items", bucketService.get(userId).getItems());
        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemsSize = request.getParameter("itemsSize");
        String itemId = request.getParameter("itemId");
        Long userId = (Long)request.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService.get(userId);
        if (itemsSize != null) {
            if (Integer.parseInt(itemsSize) > 0) {
                orderService.complete(userId, bucketService.addItemsToOrder(bucket.getId()));
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
