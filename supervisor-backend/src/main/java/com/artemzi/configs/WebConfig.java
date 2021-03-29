package com.artemzi.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
            .allowedOrigins("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        ResourceResolver resolver = new AdminResourceResolver();

        registry.addResourceHandler("/**")
                .addResourceLocations("/public/static/css/**")
                .addResourceLocations("/public/static/js/**")
                .addResourceLocations("/public/static/fonts/**")
                .addResourceLocations("/public/static/media/**")
                .addResourceLocations("/public/static/img/**")
                .resourceChain(false)
                .addResolver(resolver);

        registry.addResourceHandler("/")
                .addResourceLocations("/public/static/**")
                .resourceChain(false)
                .addResolver(resolver);
    }

    private static class AdminResourceResolver implements ResourceResolver {
        private final Resource index = new ClassPathResource("/public/index.html");

        @Override
        public Resource resolveResource(HttpServletRequest request, @NotNull String requestPath, @NotNull List<? extends Resource> locations, @NotNull ResourceResolverChain chain) {
            return resolve(requestPath, locations);
        }

        @Override
        public String resolveUrlPath(@NotNull String resourcePath, @NotNull List<? extends Resource> locations, @NotNull ResourceResolverChain chain) {
            Resource resolvedResource = resolve(resourcePath, locations);
            if (resolvedResource == null) {
                return null;
            }
            try {
                return resolvedResource.getURL().toString();
            } catch (IOException e) {
                return resolvedResource.getFilename();
            }
        }

        private Resource resolve(String requestPath, List<? extends Resource> locations) {

            if(requestPath == null) return null;

            if (
                    requestPath.startsWith("static/js") ||
                    requestPath.startsWith("static/css") ||
                    requestPath.startsWith("static/fonts") ||
                    requestPath.startsWith("static/media") ||
                    requestPath.startsWith("static/img")
            ) {
                return new ClassPathResource("/public/" + requestPath);
            } else {
                return index;
            }
        }
    }

}
