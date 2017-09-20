package ru.gothmog.websys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.websys.dao.UserProfileDao;
import ru.gothmog.websys.model.UserProfile;
import ru.gothmog.websys.service.UserProfileService;

import java.util.List;

/**
 * @author gothmog on 21.09.2017.
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao userProfileDao;

    @Override
    public UserProfile findById(long id) {
        return userProfileDao.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }
}
