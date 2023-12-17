package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getUserByCarModel(String model) {
        Car car = sessionFactory.getCurrentSession()
                .createQuery("FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.model =: model", Car.class)
                .setParameter("model", model).uniqueResult();
        System.out.println(car.getUser().getFirstName());
        System.out.println(car.getModel());
    }
}
