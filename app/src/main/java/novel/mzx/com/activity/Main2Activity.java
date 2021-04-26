package novel.mzx.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import novel.mzx.com.R;

//import novel.mzx.com.R;

public class Main2Activity extends AppCompatActivity {
private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webview= findViewById(R.id.webview);
        webview.loadUrl("http://www.baidu.com");
        webview.setWebViewClient(new WebViewClient());
    }
}
