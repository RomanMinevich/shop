package mate.academy.internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import mate.academy.internetshop.Factory;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;

public class AnnotatedClassMap {

    private static final Map<Class, Object> implementations = new HashMap<>();

    static {
        implementations.put(ItemDao.class, Factory.getItemDao());
        implementations.put(BucketDao.class, Factory.getBucketDao());
        implementations.put(OrderDao.class, Factory.getOrderDao());
        implementations.put(RoleDao.class, Factory.getRoleDao());
        implementations.put(UserDao.class, Factory.getUserDao());
        implementations.put(ItemService.class, Factory.getItemService());
        implementations.put(BucketService.class, Factory.getBucketService());
        implementations.put(OrderService.class, Factory.getOrderService());
        implementations.put(RoleService.class, Factory.getRoleService());
        implementations.put(UserService.class, Factory.getUserService());
    }

    public static Object implement(Class dependencyInterface) {
        return implementations.get(dependencyInterface);
    }
}
