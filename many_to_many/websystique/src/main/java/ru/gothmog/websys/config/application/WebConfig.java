package ru.gothmog.websys.config.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.gothmog.websys.converter.RoleToUserProfileConverter;

/**
 * @author gothmog on 05.09.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"ru.gothmog.websys.*",
        "ru.gothmog.websys.config",
        "ru.gothmog.websys.controller",
        "ru.gothmog.websys.model",
        "ru.gothmog.websys.converter",
        "ru.gothmog.websys.dao",
        "ru.gothmog.websys.service"})
public class WebConfig extends WebMvcConfigurerAdapter {
//    @Bean(name="multipartResolver")
//    public StandardServletMultipartResolver resolver(){
//        return new StandardServletMultipartResolver();
//    }
    @Autowired
    private RoleToUserProfileConverter roleToUserProfileConverter;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/views/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
    }

    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

      /* **************************************************************** */
    /*  File uploading to server throw web form                         */
    /*  setMaxUploadSize(100000000) - size in bytes, meaning about 100mb*/
    /* **************************************************************** */

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(100000000000L);
//        return multipartResolver;
//    }
}
