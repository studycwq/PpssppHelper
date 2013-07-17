package org.cwq.ppsspphelper;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	
	public static final int TOTAL_SECTIONS = 6;
	public static final int SECTION_HOME = 0;
	public static final int SECTION_DOWNLOAD_EMULATOR = 1;
	public static final int SECTION_SEARCH_GAME_ROM = 2;
	public static final int SECTION_SEARCH_GAME_ARCHIVE = 3;
	public static final int SECTION_SEARCH_GAME_GOLDFINGER = 4;
	public static final int SECTION_ABOUT = 5;

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
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_exit:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
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
			Locale l = Locale.getDefault();
			switch (position) {
			case SECTION_HOME:
				return getString(R.string.title_home).toUpperCase(l);
			case SECTION_DOWNLOAD_EMULATOR:
				return getString(R.string.title_download_emulator).toUpperCase(l);
			case SECTION_SEARCH_GAME_ROM:
				return getString(R.string.title_search_game_rom).toUpperCase(l);
			case SECTION_SEARCH_GAME_ARCHIVE:
				return getString(R.string.title_search_game_archive).toUpperCase(l);
			case SECTION_SEARCH_GAME_GOLDFINGER:
				return getString(R.string.title_search_game_goldfinger).toUpperCase(l);
			case SECTION_ABOUT:
				return getString(R.string.title_about).toUpperCase(l);
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
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
