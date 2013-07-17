package org.cwq.ppsspphelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchGameArchiveFragment extends Fragment {
	private EditText mGameArchiveNameEditText;
	private Button mSearchButton;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mGameArchiveNameEditText = (EditText) getActivity().findViewById(R.id.et_search_game_archive_name);
		mSearchButton = (Button) getActivity().findViewById(R.id.btn_search_game_archive);
		
		mSearchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = mGameArchiveNameEditText.getText().toString();
				if(name == null || name.equals("")){
					System.out.println("No input content");
					mGameArchiveNameEditText.setError(getText(R.string.edit_text_null_error_msg), 
							getResources().getDrawable(android.R.drawable.ic_dialog_alert));
					mGameArchiveNameEditText.requestFocus();
					return;
					
				}
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				Uri contentUri = Uri.parse(
						String.format(
								getString(R.string.tgbus_search_archive_link), 
								name));
				intent.setData(contentUri);
				getActivity().startActivity(intent);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_search_game_archive, container, false);
		return rootView;
	}

}
