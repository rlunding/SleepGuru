/**
 * @author Omer Muhammed
 * Copyright 2014 (c) Jawbone. All rights reserved.
 *
 */
package com.jawbone.upplatformsdk.oauth;

import android.net.Uri;
import android.util.Log;

import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;

import java.util.List;

/**
 * This class provide the API end point to make the OAuth Web View request.
 * Note that it does not use Retrofit library.
 */
public class OauthUtils {

    private static final String TAG = OauthUtils.class.getSimpleName();

    public static Uri.Builder setOauthParameters(String clientId, String callbackUrl, List<UpPlatformSdkConstants.UpPlatformAuthScope> scope) {
        Uri.Builder builder = setBaseParameters();

        builder.appendPath("auth");
        builder.appendPath("oauth2");
        builder.appendPath("auth");
        builder.appendQueryParameter("response_type", "code");
        builder.appendQueryParameter("client_id", clientId);
        builder = setOauthScopeParameters(scope, builder);
        builder.appendQueryParameter("redirect_uri", callbackUrl);

        return builder;
    }

    public static Uri.Builder setOauthScopeParameters(List<UpPlatformSdkConstants.UpPlatformAuthScope> scopeArrayList, Uri.Builder builder) {
        StringBuilder scopeValues = new StringBuilder();

        for (UpPlatformSdkConstants.UpPlatformAuthScope scope : scopeArrayList) {
            switch (scope) {

                case SLEEP_READ:
                    scopeValues.append("sleep_read ");
                    break;
                case SLEEP_WRITE:
                    scopeValues.append("sleep_write ");
                    break;
                case ALL:
                    scopeValues.append("sleep_read ");
                    scopeValues.append("sleep_write ");
                    break;
                default:
                    scopeValues = null;
                    Log.e(TAG, "unknown scope:" + scope + ", setting it to null");
                    break;
            }
        }

        if (scopeValues != null && scopeValues.length() > 0) {
            scopeValues.setLength(scopeValues.length() - 1);
            builder.appendQueryParameter("scope", scopeValues.toString());
            return builder;
        } else {
            return builder;
        }
    }

    public static Uri.Builder setBaseParameters() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(UpPlatformSdkConstants.URI_SCHEME);
        builder.authority(UpPlatformSdkConstants.AUTHORITY);

        return builder;
    }
}
