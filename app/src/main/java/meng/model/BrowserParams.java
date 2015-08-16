package meng.model;

import android.content.Intent;
import android.os.Bundle;

/**
 * Class to tune configuration of web browser
 */
public class BrowserParams {
  /**
   * Will show and update title of page when true. When false the title will be
   * always empty
   */
  public boolean showTitle = true;

  /**
   * If true - will show navigation bar on bottom of the activity. If false -
   * the bar is not showing
   */
  public boolean showNavigationToolbar = false;

  /**
   * If true - will show share button in navigation bar on bottom of the activity. If false -
   * the share button in bar is not showing
   */
  public boolean showShareButtonInNavToolbar = true;

  /**
   * Every link will be checked for possible url handlers (e.g. play.google.com
   * for Google Play app, etc.) if true. If false, every link will stay inside
   * this browser.
   */
  public boolean allowExternalUrlHandlers = true;

  /**
   * Flag to show refresh button in action bar
   */
  public boolean showRefresh = false;

  public boolean showRedirectToAppDialog = true;

  public long postId = -1;

  public long postTime = -1;

  // keys to store in bundle
  private static final String OPTIONS_KEY = "browser_options";
  private static final String SHOW_TITLE_KEY = "browser_show_title";
  private static final String SHOW_NAVBAR_KEY = "browser_show_navbar";
  private static final String SHOW_SHARE_IN_NAVBAR = "browser_show_share_in_navbar";
  private static final String ALLOW_EXTERNAL_URL_HANDLERS_KEY = "external_handlers";
  private static final String SHOW_REFRESH_KEY = "browser_show_refresh";
  private static final String SHOW_REDIRECT_TO_APP_DIALOG = "show_redirect_to_app_dialog";
  private static final String POST_ID = "post_id";
  private static final String POST_TIME = "post_time";
  private static final String JAVASCRIPT_TO_APPLY = "js_to_apply";


  public void writeToIntent(Intent intent) {
    Bundle b = new Bundle();
    b.putBoolean(SHOW_TITLE_KEY, showTitle);
    b.putBoolean(SHOW_NAVBAR_KEY, showNavigationToolbar);
    b.putBoolean(SHOW_SHARE_IN_NAVBAR, showShareButtonInNavToolbar);
    b.putBoolean(ALLOW_EXTERNAL_URL_HANDLERS_KEY, allowExternalUrlHandlers);
    b.putBoolean(SHOW_REFRESH_KEY, showRefresh);
    b.putBoolean(SHOW_REDIRECT_TO_APP_DIALOG, showRedirectToAppDialog);
    b.putLong(POST_ID, postId);
    b.putLong(POST_TIME, postTime);

    intent.putExtra(OPTIONS_KEY, b);
  }

  public void readFromIntent(Intent intent) {
    Bundle b = intent.getBundleExtra(OPTIONS_KEY);
    if (b != null) {
      showTitle = b.getBoolean(SHOW_TITLE_KEY, showTitle);
      showNavigationToolbar = b.getBoolean(SHOW_NAVBAR_KEY, showNavigationToolbar);
      showShareButtonInNavToolbar = b.getBoolean(SHOW_SHARE_IN_NAVBAR, showShareButtonInNavToolbar);
      allowExternalUrlHandlers = b.getBoolean(ALLOW_EXTERNAL_URL_HANDLERS_KEY, allowExternalUrlHandlers);
      showRefresh = b.getBoolean(SHOW_REFRESH_KEY, showRefresh);
      showRedirectToAppDialog = b.getBoolean(SHOW_REDIRECT_TO_APP_DIALOG, showRedirectToAppDialog);
      postId = b.getLong(POST_ID, postId);
      postTime = b.getLong(POST_TIME, postTime);
    }
  }
}
