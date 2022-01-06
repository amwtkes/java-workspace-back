package com.xiaojin.algorithm.base;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoading {
    @Bean
    public ResourceLoader getResourceLoader() {
        return new DefaultResourceLoader();
    }
}
