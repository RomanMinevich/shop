package mate.academy.internetshop.service.impl;

import static mate.academy.internetshop.util.HashUtil.hashPassword;

import java.util.List;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public User login(String phoneNumber, String password) throws AuthenticationException {
        User user = userDao.login(phoneNumber);
        if (user != null) {
            password = hashPassword(user.getSalt(), password);
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new AuthenticationException("Incorrect phone number or password");
    }

    @Override
    public User getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
