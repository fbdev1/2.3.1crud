package hibernate.dao;
import hibernate.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    //    private final Map<String, User> userMap = Collections.singletonMap("test",
//            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map
    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u where u.name = :name", User.class).setParameter("name", name).getSingleResult();
    }


    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u from User u").getResultList();
    }

    @Transactional
    public void remove(Long id) {

        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :id").setParameter("id", id);
        query.executeUpdate();

    }

    @Transactional
    public void update(User user, Long id) {
        User newUser = findById(id);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(user.getRoles());
        add(newUser);
    }


    @Transactional(readOnly = true)
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }


}
