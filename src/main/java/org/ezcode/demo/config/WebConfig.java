package org.ezcode.demo.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Webconfig
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { //root-context.xml
        // TODO Auto-generated method stub
        return new Class[] {RootConfig.class, SecurityConfig.class};
        //  
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return new Class[] {ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[] {"/"};
    }

    
}