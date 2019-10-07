package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    User login(String phoneNumber, String password) throws AuthenticationException;

    User getByToken(String token);

    List<User> getAllUsers();
}
