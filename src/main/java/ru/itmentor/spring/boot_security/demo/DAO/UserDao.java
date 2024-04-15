package ru.itmentor.spring.boot_security.demo.DAO;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDao {

    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional(readOnly = true)
    public List<User> index() {

        return entityManager.createQuery("select c from User c", User.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public User indexCount(long id) {
        return entityManager.getReference(User.class,id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(long id, User updated) {
        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setUsername(updated.getUsername());
        userToBeUpdated.setPassword(updated.getPassword());
        userToBeUpdated.setRoles(updated.getRoles());
    }

    @Transactional
    public void delete(long id) {
        entityManager.remove(entityManager.getReference(User.class, id));
    }

}
