package ru.gothmog.websys.service;

import ru.gothmog.websys.model.User;

import java.util.List;

/**
 * @author gothmog on 21.09.2017.
 */
public interface UserService {

    User findById(long id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Long id, String sso);
}
