package ru.arhlib.app.afisha;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ru.arhlib.app.MyWebViewClient;
import ru.arhlib.app.R;

public class AfishaFragment extends Fragment {

    public AfishaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.afisha_fragment, container, false);
        WebView webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setBackgroundColor(0);
        String style = "<style>" + getString(R.string.style_css) + "</style>";
        AfishaViewModel viewModel = ViewModelProviders.of(this).get(AfishaViewModel.class);
        viewModel.getPage(6671).observe(this, page -> {
            if (page != null) { // null on first start
                String data = style + page.getContent().replace('\u00A0', ' ');
                webView.loadData(data, "text/html; charset=UTF-8", null);
            }
        });
        return view;
    }
}