package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    private static final Logger log = Logger.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(itemService.get(1L));
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
