package ru.gothmog.websys.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.gothmog.websys.model.UserProfile;
import ru.gothmog.websys.service.UserProfileService;

/**
 * @author gothmog on 17.09.2017.
 *  A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

    @Autowired
    private UserProfileService userProfileService;
    /**
     * Gets UserProfile by Id
     * @see Converter#convert(Object)
     */
    @Override
    public UserProfile convert(Object source) {
        Long id = Long.parseLong((String) source);
        UserProfile profile = userProfileService.findById(id);
        return profile;
    }
}
