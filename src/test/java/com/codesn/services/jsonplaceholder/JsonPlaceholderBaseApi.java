package com.codesn.services.jsonplaceholder;

import com.codesn.core.BaseApi;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import com.codesn.core.utils.WaitForService;


public abstract class JsonPlaceholderBaseApi extends BaseApi {

    protected static RequestSpecification REQUEST_SPEC;

    @BeforeAll
    public static void setupJsonPlaceholder() {

        WaitForService.waitUntilAvailable(JsonPlaceholderConfig.getApiBaseUrl());

        REQUEST_SPEC = buildSpec(
                JsonPlaceholderConfig.getApiBaseUrl(),
                JsonPlaceholderConfig.getTimeout());
    }
}
