package ru.gothmog.websys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.gothmog.websys.model.User;
import ru.gothmog.websys.model.UserProfile;
import ru.gothmog.websys.service.UserProfileService;
import ru.gothmog.websys.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author d.grushetskiy
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private MessageSource messageSource;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap modelMap){
        List<User> users = userService.findAllUsers();
        modelMap.addAttribute("users", users);
        return "userlist";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", false);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        /*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError = new FieldError("user", "ssoId", messageSource
                    .getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            bindingResult.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);
        modelMap.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        return "registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap modelMap){
        User user = userService.findBySSO(ssoId);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", true);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap, @PathVariable String ssoId){
        if (bindingResult.hasErrors()){
            return "registration";
        }

        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/

        userService.updateUser(user);
        modelMap.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        return "registrationsuccess";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId){
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }

    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
}
