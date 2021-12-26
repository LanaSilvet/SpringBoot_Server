package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Validate validate;

    @Override
    public void addUser(User user) throws Exception {
        checkRoles(user);

        validateUser(user);
        userDAO.addUser(user);
    }

    public void validateUser(User user) throws Exception {

        if (getUserList(user.getName(), user.getEmail()).isEmpty()) {
            validate.checkName(user);
            validate.checkEmail(user);
            validate.checkPassword(user);
        } else {
            throw new Exception("User with a given name: " + user.getName() + " and email: " + user.getEmail() + " already exists.");
        }
    }

    public List<User> getUserList(String name, String email) {
        return userDAO.getUserList(name, email);
    }

    private void checkRoles(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            List<Role> roleByRole = roleService.getAllRoles(role);
            if (roleByRole.isEmpty()) {
                roles.add(role);
            } else {
                roles.add(roleByRole.get(0));
            }
        }
        user.setRoles(roles);
    }

    @Override
    public User updateUser(User user) throws Exception {
        checkRoles(user);

        validateUser(user);
        return userDAO.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
