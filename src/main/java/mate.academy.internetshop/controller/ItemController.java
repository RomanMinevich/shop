package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

@WebServlet("/items")
public class ItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;

    private static final Long TEMP_BUCKET_ID = 0L;

    private static final Logger log = Logger.getLogger(ItemController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("items", itemService.getAll());
        request.getRequestDispatcher("WEB-INF/views/items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemId = request.getParameter("Add");
        if (itemId != null) {
            bucketService.addItem(TEMP_BUCKET_ID, Long.valueOf(itemId));
            log.info("Item added to bucket");
            response.sendRedirect(request.getContextPath() + "/items");
        }
    }
}
