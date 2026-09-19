package com.codesn.services.jsonplaceholder;

import com.codesn.core.ConfigLoader;

public final class JsonPlaceholderConfig {

    private static final ConfigLoader CONFIG = new ConfigLoader("jsonplaceholder");

    private JsonPlaceholderConfig() {}

    public static String getApiBaseUrl() { return CONFIG.get("apiBaseUrl"); }
    public static int getTimeout()       { return Integer.parseInt(CONFIG.get("timeout")); }
}
