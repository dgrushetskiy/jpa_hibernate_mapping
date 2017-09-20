package ru.gothmog.websys.dao;

import ru.gothmog.websys.model.UserProfile;

import java.util.List;

/**
 * @author gothmog on 20.09.2017.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(long id);
}
