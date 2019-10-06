package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Set;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static RoleDao roleDao;

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
    public Set<Role> getUserRoles(Long id) {
        return roleDao.getUserRoles(id);
    }

    @Override
    public User login(String phoneNumber, String password) throws AuthenticationException {
        return userDao.login(phoneNumber, password);
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
