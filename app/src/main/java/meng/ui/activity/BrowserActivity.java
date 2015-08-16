package meng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import meng.model.BrowserParams;
import meng.olladroid.R;

public class BrowserActivity extends AppCompatActivity {

    private static final String CHROME_PACKAGE = "com.android.chrome";
    private WebView webView;
    private String originalUrl;

    /**
     * Show internal browser activity or launches external browser
     *
     * @return true if browser (internal or external) is launched
     */
    public static boolean launchBrowser(String url, Context context, BrowserParams options) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        //url can force using external browser by using special schema urls: eb_http//example.com or eb_https://example.com
        boolean forceExternalBrowser = false;
        if (url.startsWith("eb_http://") || url.startsWith("eb_https://")) {
            url = url.substring("eb_".length());
            forceExternalBrowser = true;
        }

        if (!isSupportedPlatform() || forceExternalBrowser) {
            //for old/unsupported devices we launch default browser/view activity
            Intent launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            //Check if chrome available
            launchIntent.setPackage(CHROME_PACKAGE);
            launchIntent.setPackage(null);
            return false;
        }

        final Uri uri = Uri.parse(url);
        Intent i = new Intent(context, BrowserActivity.class);
        i.setData(uri);
        if (options != null) {
            options.writeToIntent(i);
        }
        context.startActivity(i);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        webView = (WebView) findViewById(R.id.web_view);
        setUpWebView();
        Uri i = null;
        final Intent intent = getIntent();
        if (intent != null) {
            i = intent.getData();
        }

        if (i == null) {
            //empty url, just finish activity
            finish();
            return;
        }

        //start loading url
        originalUrl = i.toString();
        webView.loadUrl(originalUrl);
    }

    private void setUpWebView() {

        //Enable javascript and plugins
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);

        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_ali) {
            clearAliCookie(this);
            return true;
        } else if (id == R.id.action_clear_all) {
            CookieManager.getInstance().removeAllCookie();
            return true;
        } else if (id == R.id.action_refresh) {
            webView.loadUrl(originalUrl);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static boolean isSupportedPlatform() {
        //HTML 5 video is fully supported since ICS.
        //Also ICS browser is much better in general
        return android.os.Build.VERSION.SDK_INT >= 14;
    }


    @SuppressWarnings("deprecation")
    public static void clearAliCookie(Context context) {
        // seems secure = true or false, both is ok
        deleteWebViewCookiesForDomain(context, ".aliexpress.com", true);
    }

    @SuppressWarnings("deprecation")
    private static void deleteWebViewCookiesForDomain(Context context, String domain, boolean secure) {
        CookieSyncManager csm = CookieSyncManager.createInstance(context);
        CookieManager cm = CookieManager.getInstance();
        cm.removeSessionCookie();

    /* http://code.google.com/p/android/issues/detail?id=19294 */
        if (Build.VERSION.SDK_INT >= 11) {
            // don't trim leading '.'s
        } else {
      /* Trim leading '.'s */
            if (domain.startsWith(".")) domain = domain.substring(1);
        }

    /* Cookies are stored by domain, and are not different for different schemes (i.e. http vs
     * https) (although they do have an optional 'secure' flag.) */
        domain = "http" + (secure ? "s" : "") + "://" + domain;
        String cookieGlob = cm.getCookie(domain);
        if (cookieGlob != null) {
            String[] cookies = cookieGlob.split(";");
            for (String cookieTuple : cookies) {
                String[] cookieParts = cookieTuple.split("=");

        /* setCookie has changed a lot between different versions of Android with respect to
         * how it handles cookies like these, which are set in order to clear an existing
         * cookie.  This way of invoking it seems to work on all versions. */
                cm.setCookie(domain, cookieParts[0] + "=;");
        /* These calls have worked for some subset of the the set of all versions of
         * Android:
         * cm.setCookie(domain, cookieParts[0] + "=");
         * cm.setCookie(domain, cookieParts[0]); */
            }
            csm.sync();
        }
    }
}
