package org.cwq.ppsspphelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class DownloadEmulatorFragment extends Fragment {
	private Spinner mPlatformSpinner;
	private String[] mPlatforms = null;
	private String[] mPlatformLinks = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mPlatformSpinner = (Spinner) getActivity().findViewById(R.id.spn_download_emulator_platform);
		mPlatformSpinner.setOnItemSelectedListener(mPlatformOnClickListener);
		
		//Initial array
		mPlatforms = getActivity().getResources().getStringArray(R.array.emulator_platform_entries);
		mPlatformLinks = getActivity().getResources().getStringArray(R.array.emulator_platform_values);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_download_emulator, container, false);
		return rootView;
	}

	private OnItemSelectedListener mPlatformOnClickListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			if (position == 0)
				return;
			if (mPlatforms[position].equals("IOS")) {
				new AlertDialog.Builder(getActivity())
				.setMessage(R.string.ios_confirm_dialog_msg)
				.setTitle(android.R.string.dialog_alert_title)
				.setPositiveButton(android.R.string.ok, null)
				.show();
			} else {
				final String link = mPlatformLinks[position];
				new AlertDialog.Builder(getActivity())
				.setMessage(getActivity().getResources().getString(R.string.confirm_dialog_msg, mPlatforms[position]))
				.setTitle(android.R.string.dialog_alert_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						Uri contentUri = Uri.parse(link);
						intent.setData(contentUri);
						getActivity().startActivity(intent);
					}
				})
				.setNegativeButton(android.R.string.no, null)
				.show();
			}
			mPlatformSpinner.setSelection(0);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};

}
