package rs.rnk.tasks.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import rs.rnk.tasks.rest.exception.AppExceptionMapper;
import rs.rnk.tasks.rest.exception.GenericExceptionMapper;
import rs.rnk.tasks.rest.resource.UserResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.register(UserResource.class);
        this.register(AppExceptionMapper.class);
        this.register(GenericExceptionMapper.class);
    }

}
