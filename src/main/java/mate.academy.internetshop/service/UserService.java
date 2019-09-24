package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    List<Order> getOrders(Long userId);

    User login(String phoneNumber, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
