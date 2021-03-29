package com.artemzi.configs;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ConfigCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
        String driverClassName = context.getEnvironment().getProperty("spring.datasource.driver-class-name");
        String url = context.getEnvironment().getProperty("spring.datasource.url");
        String username = context.getEnvironment().getProperty("spring.datasource.username");
        String password = context.getEnvironment().getProperty("spring.datasource.password");

        Assert.notNull(driverClassName, "Укажите \"spring.datasource.driver-class-name\"");
        Assert.notNull(url, "Укажите \"spring.datasource.url\"");
        Assert.notNull(username, "Укажите \"spring.datasource.username\"");
        Assert.notNull(password, "Укажите \"spring.datasource.password\"");
        return true;
    }
}
