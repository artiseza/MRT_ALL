package com.example.user.a123;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TimeSerch extends Fragment {

    private int idx = 0;


    // TODO: Rename and change types and number of parameters
    public static TimeSerch newInstance(String param1, String param2) {
        TimeSerch fragment = new TimeSerch();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    WebView mWebView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idx = (int) getArguments().get("idx");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.time_serch, container, false);
        mWebView = (WebView)view.findViewById(R.id.mybrowser);
        WebSettings websettings = mWebView.getSettings();
        websettings.setSupportZoom(false);
        websettings.setBuiltInZoomControls(false);
        websettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(mWebViewClient);

        mWebView.loadUrl("http://data.but.tw/trtc/price.html");

        return view;
    }
    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}