package org.lunding.sleepguru;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by David on 13/9/14.
 */
public class MainActivity extends Activity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    // These are obtained after registering on Jawbone Developer Portal
    // Credentials used here are created for "Test-App1"
    private static final String CLIENT_ID = "_W1Vw3ksfpQ";
    private static final String CLIENT_SECRET = "ed46a27e5d3441317607bac4ea99de9617790637";

    // This has to be identical to the OAuth redirect url setup in Jawbone Developer Portal
    private static final String OAUTH_CALLBACK_URL = "http://localhost/helloup?";

//    private Intent getIntentForWebView() {
//        Uri.Builder builder = UpPlatformSdkUtils.setOauthParameters(CLIENT_ID, OAUTH_CALLBACK_URL, authScope);
//
//        Intent intent = new Intent(OauthWebViewActivity.class.getName());
//        intent.putExtra(UpPlatformSdkUtils.AUTH_URI, builder.build());
//        return intent;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
