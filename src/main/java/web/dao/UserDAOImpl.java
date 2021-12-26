package web.dao;

import org.springframework.stereotype.Service;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT user FROM User user";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        String jpql = "SELECT user FROM User user WHERE user.name = :name";
        return em.createQuery(jpql, User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User userById = getUserById(id);
        em.remove(userById);
    }

    @Override
    public List<User> getUserList(String name, String email) {
        String jpql = "SELECT user FROM User user WHERE user.name = :name AND user.email = :email";
        List<User> resultList = em.createQuery(jpql, User.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .getResultList();
        return resultList;
    }
}
