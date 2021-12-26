package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user) throws Exception;

    User updateUser(User user) throws Exception;

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String name);

    void deleteUser(int id);
}
