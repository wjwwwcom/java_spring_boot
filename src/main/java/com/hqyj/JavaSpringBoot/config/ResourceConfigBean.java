package com.hqyj.JavaSpringBoot.config;

import com.sun.java.swing.plaf.windows.resources.windows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/application.properties")
public class ResourceConfigBean {

    @Value("${spring.resource.path}")
    private String resourcePath;
    @Value("${spring.resource.path.pattern}")
    private String resourcePathPattern;
    @Value("${spring.resource.folder.windows}")
    private String resourceFolderWindows;
    @Value("${spring.resource.folder.linux}")
    private String resourceFolderLinux;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePathPattern() {
        return resourcePathPattern;
    }

    public void setResourcePathPattern(String resourcePathPattern) {
        this.resourcePathPattern = resourcePathPattern;
    }

    public String getResourceFolderWindows() {
        return resourceFolderWindows;
    }

    public void setResourceFolderWindows(String resourceFolderWindows) {
        this.resourceFolderWindows = resourceFolderWindows;
    }

    public String getResourceFolderLinux() {
        return resourceFolderLinux;
    }

    public void setResourceFolderLinux(String resourceFolderLinux) {
        this.resourceFolderLinux = resourceFolderLinux;
    }
}
