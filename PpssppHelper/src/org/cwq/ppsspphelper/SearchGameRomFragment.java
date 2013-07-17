package org.cwq.ppsspphelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchGameRomFragment extends Fragment {
	private Spinner mGameLibrarySpinner;
	private EditText mGameRomNameEditText;
	private Button mSearchButton;
	private String[] mLibraryLinks;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mLibraryLinks = getActivity().getResources().getStringArray(R.array.game_library_values);
		mGameLibrarySpinner = (Spinner) getActivity().findViewById(R.id.spn_search_game_rom_library);
		mGameRomNameEditText = (EditText) getActivity().findViewById(R.id.et_search_game_rom_name);
		mSearchButton = (Button) getActivity().findViewById(R.id.btn_search_game_rom);
		
		mSearchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = mGameRomNameEditText.getText().toString();
				if(name == null || name.equals("")){
					System.out.println("No input content");
					mGameRomNameEditText.setError(getText(R.string.edit_text_null_error_msg), 
							getResources().getDrawable(android.R.drawable.ic_dialog_alert));
					mGameRomNameEditText.requestFocus();
					return;
					
				}
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				Uri contentUri = Uri.parse(
						String.format(
								mLibraryLinks[mGameLibrarySpinner.getSelectedItemPosition()], 
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
		View rootView = inflater.inflate(R.layout.fragment_search_game_rom, container, false);
		return rootView;
	}

}
