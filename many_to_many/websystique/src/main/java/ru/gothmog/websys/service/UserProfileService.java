package ru.gothmog.websys.service;

import ru.gothmog.websys.model.UserProfile;

import java.util.List;

/**
 * @author gothmog on 21.09.2017.
 */
public interface UserProfileService {

    UserProfile findById(long id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
