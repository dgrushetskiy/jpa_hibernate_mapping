package ru.gothmog.websys.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.websys.dao.UserDao;
import ru.gothmog.websys.dao.UserProfileDao;
import ru.gothmog.websys.dao.impl.UserDaoImpl;
import ru.gothmog.websys.dao.impl.UserProfileDaoImpl;
import ru.gothmog.websys.model.User;
import ru.gothmog.websys.model.UserProfile;

/**
 * @author gothmog on 05.09.2017.
 */
@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao(){
        return new UserDaoImpl();
    }

    @Bean
    public UserProfileDao userProfileDao(){
        return new UserProfileDaoImpl();
    }
}
