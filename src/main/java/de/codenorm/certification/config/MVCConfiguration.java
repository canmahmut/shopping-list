package de.codenorm.certification.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.List;


@Configuration
public class MVCConfiguration extends WebMvcConfigurerAdapter {




    @Autowired
    private ApplicationContext context;


    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver(sortResolver());
        pageableHandlerMethodArgumentResolver.setPageParameterName("page");
        pageableHandlerMethodArgumentResolver.setOneIndexedParameters(true);
        pageableHandlerMethodArgumentResolver.setSizeParameterName("count");
        return pageableHandlerMethodArgumentResolver;
    }


    @Bean
    public MySortHandlerMethodArgumentResolver sortResolver() {
        return new MySortHandlerMethodArgumentResolver();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {

        if (!(registry instanceof FormattingConversionService)) {
            return;
        }

        registerDomainClassConverterFor((FormattingConversionService) registry);
    }

    private void registerDomainClassConverterFor(FormattingConversionService conversionService) {

        DomainClassConverter<FormattingConversionService> converter = new DomainClassConverter<>(
                conversionService);
        converter.setApplicationContext(context);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(sortResolver());
        argumentResolvers.add(pageableResolver());
    }





    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("5MB");
        factory.setMaxRequestSize("5MB");
        return factory.createMultipartConfig();
    }

}
