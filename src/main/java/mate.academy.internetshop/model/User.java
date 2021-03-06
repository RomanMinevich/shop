package mate.academy.internetshop.model;

import static mate.academy.internetshop.util.HashUtil.createSalt;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private Long id;
    private String token;
    private byte[] salt;
    private String phoneNumber;
    private String password;
    private String name;
    private String address;
    private String email;
    private final Set<Role> roles;

    public User() {
        this.token = UUID.randomUUID().toString();
        this.salt = createSalt();
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
