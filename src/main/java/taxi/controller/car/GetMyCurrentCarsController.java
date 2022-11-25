package taxi.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taxi.lib.Injector;
import taxi.service.CarService;

@WebServlet(urlPatterns = "/driver/cars")
public class GetMyCurrentCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final CarService carService = (CarService) injector.getInstance(CarService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        req.setAttribute("cars", carService.getAllByDriver(driverId));
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/all.jsp").forward(req, resp);
    }
}
