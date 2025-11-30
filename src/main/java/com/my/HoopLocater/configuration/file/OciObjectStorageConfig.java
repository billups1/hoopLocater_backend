package com.my.HoopLocater.configuration.file;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class OciObjectStorageConfig {

    @Value("${oci.config.file-path}")
    private String configFilePath;
    @Value("${oci.config.profile}")
    private String profile;

    @Bean
    public AuthenticationDetailsProvider authenticationDetailsProvider() throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parse(configFilePath, profile);
        return new ConfigFileAuthenticationDetailsProvider(configFile);
    }

    @Bean
    public ObjectStorageClient objectStorageClient(AuthenticationDetailsProvider provider) {
        return ObjectStorageClient.builder()
                .build(provider);
    }
}
