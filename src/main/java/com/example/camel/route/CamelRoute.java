package com.example.camel.route;


import com.example.camel.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {


    private final Environment env;

    public CamelRoute(Environment env) {
        this.env = env;
    }


    public void configure() {

       restConfiguration().component("http").host("localhost").port(8976).bindingMode(RestBindingMode.json);


       //TODO
        from("activemq:sendPostRequest").autoStartup(true)
                .routeId("postRequestRoute")
                .log("sending record").setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8976/v1/sample/saveEmployeeDetails")  // Send the HTTP POST request
                .log("Response from POST request:");

    }


}
