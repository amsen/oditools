package am.tarento.oditools.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepositoryConfiguraionReader {

    @Value("${odi.repo.config.path: }")
    private String configurationDirPath;

    @Bean
    public Properties configurationProperties() throws IOException{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Properties properties = new Properties();

        if (configurationDirPath.trim().isEmpty()){
            System.out.println("Missing odi.repo.config.path value. Please set the same in the properties file.");
        }

        for (org.springframework.core.io.Resource resource : resolver.getResources("classpath*:" + configurationDirPath + "/**/*.properties")) {
            properties.putAll(PropertiesLoaderUtils.loadProperties(resource));
        }
        return properties;
    }
}
