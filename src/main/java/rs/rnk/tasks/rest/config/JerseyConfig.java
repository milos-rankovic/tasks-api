package rs.rnk.tasks.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import rs.rnk.tasks.rest.exception.AppExceptionMapper;
import rs.rnk.tasks.rest.exception.GenericExceptionMapper;
import rs.rnk.tasks.rest.exception.ValidationExceptionMapper;
import rs.rnk.tasks.rest.exception.WebApplicationExceptionMapper;
import rs.rnk.tasks.rest.resource.TaskResource;
import rs.rnk.tasks.rest.resource.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        registerClasses(UserResource.class,
                TaskResource.class,
                AppExceptionMapper.class,
                WebApplicationExceptionMapper.class,
                GenericExceptionMapper.class,
                ValidationExceptionMapper.class);


    }
}
