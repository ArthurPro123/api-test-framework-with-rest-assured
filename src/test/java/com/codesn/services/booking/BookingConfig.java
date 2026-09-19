package com.codesn.services.booking;

import com.codesn.core.ConfigLoader;

public final class BookingConfig {

    private static final ConfigLoader CONFIG = new ConfigLoader("booking");

    private BookingConfig() {}

    public static String getApiBaseUrl()   { return CONFIG.get("apiBaseUrl"); }
    public static int getTimeout()         { return Integer.parseInt(CONFIG.get("timeout")); }
    public static String getAuthPath()     { return CONFIG.get("authPath"); }
    public static String getAuthUsername() { return CONFIG.get("authUsername"); }
    public static String getAuthPassword() { return CONFIG.get("authPassword"); }
}
