package com.luckyRepair.seungme.luckySeungCar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckyRepair.seungme.luckySeungCar.common.converter.JsonFormHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    private ObjectMapper mapper;

    public WebMvcConfig(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/static/lib/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        JsonFormHttpMessageConverter<?> converter = new JsonFormHttpMessageConverter<>();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        converters.add(converter);

        converters.stream()
                .forEach(x -> {
                    log.debug("converter : {}, mediaType: {} ", x, x.getSupportedMediaTypes());
                    if(x instanceof MappingJackson2HttpMessageConverter)
                        ((MappingJackson2HttpMessageConverter) x).setObjectMapper(mapper);
                });
    }
}
