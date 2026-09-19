package com.codesn.core.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WaitForService {

    private static final Logger logger = LoggerFactory.getLogger(WaitForService.class);

    public static final int DEFAULT_TIMEOUT_SECONDS = 10;
    private static final long POLL_INTERVAL_MS = 1_000;


    public static void waitUntilAvailable(String url) {
        waitUntilAvailable(url, DEFAULT_TIMEOUT_SECONDS);
    }

    public static void waitUntilAvailable(String url, int timeoutLimit) {
        while (true) {
            if (timeoutLimit == 0) {
                fail("Unable to connect to Web API");
            }

            try {
                Response response = given().get(url);

                if (response.statusCode() == 200) {
                    break;
                }

                logger.info("API returned {}, retrying...", response.statusCode());
            } catch (Exception exception) {
                logger.info("API not reachable, retrying...");
            }

            timeoutLimit--;
            sleep(POLL_INTERVAL_MS);
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Interrupted while waiting for service");
        }
    }
}
