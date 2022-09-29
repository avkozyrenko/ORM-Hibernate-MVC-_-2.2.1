package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void getUserByCar(String model, int series) {
        try (Session session = sessionFactory.openSession().getSession()) {
            TypedQuery<User> query = session.createQuery("FROM User user where car.model = :model and car.series = :series");
            query.setParameter("model", model).setParameter("series", series);
            System.out.println(query.setMaxResults(1).getSingleResult());
        } catch (HibernateException | NoResultException e) {
            e.printStackTrace();
            System.out.println("Такого пользователя нет");
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
