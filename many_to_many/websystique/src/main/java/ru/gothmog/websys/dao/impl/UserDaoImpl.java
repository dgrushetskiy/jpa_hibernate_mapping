package ru.gothmog.websys.dao.impl;

import org.springframework.stereotype.Repository;
import ru.gothmog.websys.dao.AbstractDao;
import ru.gothmog.websys.dao.UserDao;
import ru.gothmog.websys.model.User;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

/**
 * @author gothmog on 20.09.2017.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public User findById(long id) {
        User user = getByKey(id);
        if (user!=null){
            initializeCollection(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public User findBySSO(String sso) {
        System.out.println("SSO : "+sso);
        try{
            User user = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                    .setParameter("ssoId", sso)
                    .getSingleResult();

            if(user!=null){
                initializeCollection(user.getUserProfiles());
            }
            return user;
        }catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteBySSO(String sso) {
        User user = (User) getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                .setParameter("ssoId", sso)
                .getSingleResult();
        delete(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT u FROM User u ORDER BY u.firstName ASC")
                .getResultList();
        return users;
    }

    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }
}
