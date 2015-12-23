package com.share;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by wahyuoi on 9/3/15.
 */
public class AppConfiguration extends Configuration {
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();
    public DataSourceFactory getDataSourceFactory(){
        return database;
    }

    @Valid
    @JsonProperty
    private ImmutableList<String> allowedGrantTypes;
    public ImmutableList<String> getAllowedGrantTypes(){
        return allowedGrantTypes;
    }

    @Valid
    @JsonProperty
    @NotEmpty
    private String bearerRealm;
    public String getBearerRealm(){
        return bearerRealm;
    }

    @JsonProperty
    private String imagePath;
    public String getImagePath(){
        return imagePath;
    }

    @JsonProperty
    private String imageUrl;
    public String getImageUrl(){
        return imageUrl;
    }

    @Valid
    @NotNull
    @JsonProperty
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();
    public JerseyClientConfiguration getHttpClient(){
        return httpClient;
    }

    @JsonProperty
    private String parseAppId;
    public String getParseAppId(){
        return parseAppId;
    }

    @JsonProperty
    private String parseRestApi;
    public String getParseRestApi(){
        return parseRestApi;
    }
}
