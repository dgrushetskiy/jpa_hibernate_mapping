package ru.gothmog.websys.dao.impl;

import org.springframework.stereotype.Repository;
import ru.gothmog.websys.dao.AbstractDao;
import ru.gothmog.websys.dao.UserProfileDao;
import ru.gothmog.websys.model.UserProfile;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author gothmog on 20.09.2017.
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Long, UserProfile> implements UserProfileDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = getEntityManager()
                .createQuery("SELECT p FROM UserProfile p  ORDER BY p.type ASC")
                .getResultList();
        return userProfiles;
    }

    @Override
    public UserProfile findByType(String type) {
        System.out.println("type: "+type);
        try{
            UserProfile userProfile = (UserProfile) getEntityManager()
                    .createQuery("SELECT p FROM UserProfile p WHERE p.type LIKE :type")
                    .setParameter("type", type)
                    .getSingleResult();
            return userProfile;
        }catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public UserProfile findById(long id) {
        return  getByKey(id);
    }
}
