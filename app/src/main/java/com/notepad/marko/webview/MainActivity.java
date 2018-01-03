package com.notepad.marko.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
{
    public String uri = "https://login.salesforce.com/?locale=eu";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWebPage();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.open_in_browser:
                openInDefBrowser();
                return true;
            case R.id.exit:
                close();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void close()
    {
        System.exit(0);
    }

    void openInDefBrowser()
    {
        Intent browserIntetn;
        browserIntetn = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(browserIntetn);
    }
    void setWebPage()
    {
        WebView mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient()
        {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebview .loadUrl(uri);
        setContentView(mWebview );
    }
}
