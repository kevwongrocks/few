package com.kevwong.few;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ActionBar;
//import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ViewPagerActivity extends FragmentActivity {
	
	static ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	
	static int[] attackSequence = new int[9];
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);
        
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText("1st"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("2nd"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("3rd"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("4th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("5th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("6th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("7th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("8th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("9th"), SetAttack.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Attack"), StartAttack.class, null);
        
        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }

    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}
	
	public static class TabsAdapter extends FragmentPagerAdapter
		implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
		
		private final Context mContext;
        private final ActionBar mActionBar;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
        
        static final class TabInfo {
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(Class<?> _class, Bundle _args) {
                clss = _class;
                args = _args;
            }
        }

		public TabsAdapter(FragmentActivity activity, ViewPager pager) {
			super(activity.getSupportFragmentManager());
            mContext = activity;
            mActionBar = activity.getActionBar();
            mViewPager = pager;
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
            TabInfo info = new TabInfo(clss, args);
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mActionBar.addTab(tab);
            notifyDataSetChanged();
        }

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Object tag = tab.getTag();
            for (int i=0; i<mTabs.size(); i++) {
                if (mTabs.get(i) == tag) {
                    mViewPager.setCurrentItem(i);
                }
            }
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			
			//Log.d("Info", Integer.toString(position));
			
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

	}
	
	/*void switchFragment(int target){
    	mViewPager.setCurrentItem(target);
    }*/
    
    public static void fireFragment(){
    	int current = mViewPager.getCurrentItem();
    	int next = current + 1;
    	int element = 0;
    	mViewPager.setCurrentItem(next);
    	setAttack(current, element);
    }
    
    public static void earthFragment(){
    	int current = mViewPager.getCurrentItem();
    	int next = current + 1;
    	int element = 1;
    	mViewPager.setCurrentItem(next);
    	setAttack(current, element);
    }
    
    public static void waterFragment(){
    	int current = mViewPager.getCurrentItem();
    	int next = current + 1;
    	int element = 2;
    	mViewPager.setCurrentItem(next);
    	setAttack(current, element);
    }
    
    public static void setAttack(int theCurrent, int element){
    	
    	attackSequence[theCurrent] = element;
    	
    }
    
    public static int[] getAttackSequence() {
    	
    	int[] send = attackSequence;  
    	
    	return send;
    }
}