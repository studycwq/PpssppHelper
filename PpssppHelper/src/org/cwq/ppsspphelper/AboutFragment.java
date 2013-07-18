package org.cwq.ppsspphelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//Load the about page here
		WebView webView = new WebView(getActivity().getApplicationContext());
		webView.loadUrl("file:///android_asset/web/About.html");
		webView.setWebViewClient(new MyWebViewClient());
		return webView;
	}
	
	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			//Handing when click hyper link action
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			Uri contentUri = Uri.parse(url);
			intent.setData(contentUri);
			startActivity(intent);
			return true;
		}
		
	}

}
