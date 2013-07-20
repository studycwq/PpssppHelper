package org.cwq.ppsspphelper;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
	
	public static final int TOTAL_SECTIONS = 6;
	public static final int SECTION_HOME = 0;
	public static final int SECTION_DOWNLOAD_EMULATOR = 1;
	public static final int SECTION_SEARCH_GAME_ROM = 2;
	public static final int SECTION_SEARCH_GAME_ARCHIVE = 3;
	public static final int SECTION_SEARCH_GAME_GOLDFINGER = 4;
	public static final int SECTION_ABOUT = 5;
	
	private boolean exitFlag = false;
	private long lastClickBack = 0L;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Special handle for MeiZu MX2
		supportSamrtBar();
		
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}
	
	/**
	 * Support MeiZu MX2 SmartBar
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void supportSamrtBar() {
		//Build.BOARD = "mx2" Build.MODEL = "M40"
		if(Build.BOARD.equals("mx2")){
			getWindow().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
			setTheme(android.R.style.Theme_DeviceDefault_Light);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_exit:
			finish();
			return true;
		case R.id.action_config:
			Intent intent = new Intent(MainActivity.this, ConfigPpssppActivity.class);
			startActivity(intent);
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		//double click back to exit program within 2s
		if(event.getAction() == KeyEvent.ACTION_UP) {
			if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
				if(exitFlag){
					if(System.currentTimeMillis() - lastClickBack < 2000){
						exitFlag = false;
						finish();
					} else {
						lastClickBack = System.currentTimeMillis();
					}
				} else {
					exitFlag = true;
					lastClickBack = System.currentTimeMillis();
				}
				if(exitFlag)
					Toast.makeText(getApplicationContext(), R.string.double_click_exit_prompt, Toast.LENGTH_SHORT).show();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			switch (position) {
			case SECTION_HOME:
				fragment = Fragment.instantiate(getApplicationContext(), 
						HomeFragment.class.getName());
				break;
			case SECTION_DOWNLOAD_EMULATOR:
				fragment = Fragment.instantiate(getApplicationContext(), 
						DownloadEmulatorFragment.class.getName());
				break;
			case SECTION_SEARCH_GAME_ROM:
				fragment = Fragment.instantiate(getApplicationContext(), 
						SearchGameRomFragment.class.getName());
				break;
			case SECTION_SEARCH_GAME_ARCHIVE:
				fragment = Fragment.instantiate(getApplicationContext(), 
						SearchGameArchiveFragment.class.getName());
				break;
			case SECTION_SEARCH_GAME_GOLDFINGER:
				fragment = Fragment.instantiate(getApplicationContext(), 
						SearchGameGoldfingerFragment.class.getName());
				break;
			case SECTION_ABOUT:
				fragment = Fragment.instantiate(getApplicationContext(), 
						AboutFragment.class.getName());
				break;

			default:
				fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			} 
			
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 5 total pages.
			return TOTAL_SECTIONS;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			//Locale l = Locale.getDefault();
			switch (position) {
			case SECTION_HOME:
				return getString(R.string.title_home);
			case SECTION_DOWNLOAD_EMULATOR:
				return getString(R.string.title_download_emulator);
			case SECTION_SEARCH_GAME_ROM:
				return getString(R.string.title_search_game_rom);
			case SECTION_SEARCH_GAME_ARCHIVE:
				return getString(R.string.title_search_game_archive);
			case SECTION_SEARCH_GAME_GOLDFINGER:
				return getString(R.string.title_search_game_goldfinger);
			case SECTION_ABOUT:
				return getString(R.string.title_about);
			}
			return null;
		}
	}

	

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(android.R.layout.simple_list_item_1,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(android.R.id.text1);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
