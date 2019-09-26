package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import mate.academy.internetshop.generator.UserIdGenerator;

public class User {
    private final Long id;
    private Long bucketId;
    private final Set<Role> roles;
    private final List<Order> orders;
    private final String token;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String shippingAddress;
    private String email;

    public User() {
        this.id = UserIdGenerator.generateId();
        this.token = UUID.randomUUID().toString();
        this.roles = new HashSet<>();
        this.orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
