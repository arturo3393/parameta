package com.parameta.challenge.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class for managing the SOAP server using a WsConfigurerAdapter.
 * This class handles the necessary configuration for the SOAP server.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Handles the configuration of the message dispatcher.
     *
     * @param applicationContext the application context to handle the dispatcher
     * @return the servlet registration bean
     * @throws IllegalArgumentException when parameters are not valid
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Defines the default WSDL definition for the "employeeSoap" web service.
     *
     * @param employeeSchema the XSD schema of the employee service
     * @return the WSDL definition of the "employeeSoap" service
     * @throws IllegalArgumentException when parameters are not valid
     */
    @Bean(name = "employeeSoap")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("employeeSoapPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost:8081/api/v1/employee");
        wsdl11Definition.setSchema(employeeSchema);
        return wsdl11Definition;
    }


    /**
     * Creates the XSD schema for the employee service.
     *
     * @return the XSD schema of the employee service
     * @throws IllegalArgumentException when parameters are not valid
     */
    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employeeSoap.xsd"));
    }
}