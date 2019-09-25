package mate.academy.internetshop.dao.impl;

import static java.util.stream.IntStream.range;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        if (Storage.users
                .stream()
                .anyMatch(element -> element.getId().equals(user.getId()))) {
            throw new IllegalArgumentException("Can't add user with id " + user.getId());
        }
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(element -> element.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find user with id " + id));
    }

    @Override
    public User update(User user) {
        int index = range(0, Storage.users.size())
                .filter(element -> Storage.users.get(element).getId().equals(user.getId()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find user with id " + user.getId()));
        Storage.users.set(index, user);
        return user;
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        Storage.users.removeIf(element -> element.equals(user));
        return user;
    }

    @Override
    public User login(String phoneNumber, String password) throws AuthenticationException {
        Optional<User> user = Storage.users
                .stream()
                .filter(element -> element.getPhoneNumber().equals(phoneNumber))
                .findAny();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect phone number or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users
                .stream()
                .filter(element -> element.getToken().equals(token))
                .findAny();
    }
}
