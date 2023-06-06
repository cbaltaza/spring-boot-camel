package io.fabric8.quickstarts.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;
import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception { // @formatter:off
        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.json)
        .dataFormatProperty("prettyPrint", "true")
        .apiContextPath("/api-doc")
            .apiProperty("api.title", "User API").apiProperty("api.version", "1.0.0")
            .apiProperty("cors", "true");

    
    rest("/users").description("User REST service")
        .consumes("application/json")
        .produces("application/json")

        .get().description("Find all users").outType(User[].class)
            .responseMessage().code(200).message("All users successfully returned").endResponseMessage()
            .to("bean:userService?method=findUsers")
    
        .get("/{id}").description("Find user by ID")
            .outType(User.class)
            .param().name("id").type(path).description("The ID of the user").dataType("integer").endParam()
            .responseMessage().code(200).message("User successfully returned").endResponseMessage()
            .to("bean:userService?method=findUser(${header.id})")

        .put("/{id}").description("Update a user").type(User.class)
            .param().name("id").type(path).description("The ID of the user to update").dataType("integer").endParam()    
            .param().name("body").type(body).description("The user to update").endParam()
            .responseMessage().code(204).message("User successfully updated").endResponseMessage()
            .to("bean:userService?method=updateUser");

    // @formatter:on}
    }
}
