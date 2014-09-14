/**
 * @author Omer Muhammed
 * Copyright 2014 (c) Jawbone. All rights reserved.
 *
 */
package com.jawbone.upplatformsdk.utils;

/**
 * Small class to hold the constants
 */
public class UpPlatformSdkConstants {

    public static final String URI_SCHEME = "https";
    public static final String AUTHORITY = "jawbone.com";

    public static final String API_VERSION = "version";
    public static final String API_VERSION_STRING = "v.1.1";
    public static final String XID = "xid";

    public static final String UP_PLATFORM_ACCESS_TOKEN = "access_token";
    public static final String UP_PLATFORM_REFRESH_TOKEN = "refresh_token";

    public static final String AUTH_URI = "auth_uri";
    public static final String ACCESS_CODE = "code";
    public static final String CLIENT_SECRET = "client_secret";
    public static final int JAWBONE_AUTHORIZE_REQUEST_CODE = 120501;
    public static final String API_URL = "https://jawbone.com";

    /**
     * Different types of API permissions that can be requested, defined as an enum
     */
    public enum UpPlatformAuthScope {
        SLEEP_READ,
        SLEEP_WRITE,
        ALL;
    }

    /**
     * Different API calls, defined in an enum, to facilitate reuse.
     */
    public static enum RestApiRequestType {
        GET_SLEEP_EVENTS_LIST("Get Sleep Events List"), //1
        GET_SLEEP_EVENT("Get Sleep Event"), //2
        GET_SLEEP_GRAPH("Get Sleep Graph"), //3
        GET_SLEEP_TICKS("Get Sleep Ticks"),; //4


        private String displayTitle;

        RestApiRequestType(String s) {
            this.displayTitle = s;
        }

        @Override
        public String toString() {
            return displayTitle;
        }

        public static final int size = RestApiRequestType.values().length;
    }
}
