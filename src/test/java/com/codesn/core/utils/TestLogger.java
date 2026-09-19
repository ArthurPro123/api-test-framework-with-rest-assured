package com.codesn.core.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);
    private static final ObjectMapper mapper = new ObjectMapper()
        .findAndRegisterModules();
    
    private static final String SEPARATOR = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    
    private static String getCallingMethodInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            String className = element.getClassName();
            if (!className.equals(TestLogger.class.getName())) {
                String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
                return simpleClassName + "." + element.getMethodName();
            }
        }
        return "UNKNOWN";
    }
    
    public static void log(String context, Object data) {
        String methodInfo = getCallingMethodInfo();
        
        // Determine the data type for display
        String dataType = determineDataType(data);
        
        logger.info(SEPARATOR);
        logger.info("▶ {} {} [Test: {}]", context.toUpperCase(), dataType, methodInfo);
        
        if (data instanceof Response) {
            Response response = (Response) data;
            logger.info("  Status: {}", response.getStatusCode());
            String body = response.getBody().asString();
            logBody(body);
        } else if (data instanceof String) {
            String strData = (String) data;
            // Check if it's a JSON string
            if (strData.startsWith("{") || strData.startsWith("[")) {
                logBody(strData);
            } else {
                logger.info("  Message: {}", strData);
            }
        } else {
            // Request payload or any object
            try {
                String prettyJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);
                logger.info("  Payload:\n{}", prettyJson);
            } catch (Exception e) {
                logger.info("  Data: {}", data);
            }
        }
        
        logger.info(SEPARATOR);
        logger.info("");
    }
    
    private static String determineDataType(Object data) {
        if (data == null) {
            return "NULL";
        }
        
        if (data instanceof Response) {
            return "RESPONSE";
        }
        
        if (data instanceof String) {
            String strData = (String) data;
            if (strData.startsWith("{") || strData.startsWith("[")) {
                return "JSON";
            }
            return "MESSAGE";
        }
        
        return "PAYLOAD";
    }
    
    private static void logBody(String body) {
        if (body != null && !body.isEmpty()) {
            try {
                Object json = mapper.readValue(body, Object.class);
                String prettyJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(json);
                logger.info("  Body:\n{}", prettyJson);
            } catch (Exception e) {
                logger.info("  Body: {}", body);
            }
        }
    }
}
