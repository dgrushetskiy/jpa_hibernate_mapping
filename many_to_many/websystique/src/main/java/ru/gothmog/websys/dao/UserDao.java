package ru.gothmog.websys.dao;

import ru.gothmog.websys.model.User;

import java.util.List;

/**
 * @author gothmog on 20.09.2017.
 */
public interface UserDao {

    User findById(long id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}
