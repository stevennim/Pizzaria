package com.hfad.pizzasupplibraryandappbars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider sap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set this activity's app bar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //attach the SectionsPagerAdapter (allows fragments to be on pagers)
        //to the ViewPager so fragments can be displayed on the app's view pager
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout so each section can be accessed by the tabs or by swiping
        TabLayout t = (TabLayout) findViewById(R.id.tabs);
        t.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){//runs when app bar is created
        //inflate the menu; parse xml data to create menu views
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem mi = menu.findItem(R.id.action_share);//extract the share action
        sap = (ShareActionProvider) MenuItemCompat.getActionProvider(mi);//fetch share action
        setShareActionIntent("Wanna spawn camp with me?");
        return super.onCreateOptionsMenu(menu);//add created views to app bar
    }

    private void setShareActionIntent(String text){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, text);//add given text to share action's message
        sap.setShareIntent(i);//set the share action's intent
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){//runs when an action in app bar is clicked
        switch(item.getItemId()){
            case R.id.action_create_order://create order button
                Intent i = new Intent(this, OrderActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //this class is used to populate each section of the pager with a different fragment
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount(){//there are 4 pages in the view pager
            return 4;
        }

        @Override
        public Fragment getItem(int pos){//assigns positions for each fragment to be displayed in the pager
            switch(pos){
                case 0:
                    return new TopFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return new StoresFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position){//adds text to each tab
            switch(position){
                case 0:
                    return getResources().getText(R.string.home_tab);
                case 1:
                    return getResources().getText(R.string.pizzas_tab);
                case 2:
                    return getResources().getText(R.string.pasta_tab);
                case 3:
                    return getResources().getText(R.string.stores_tab);
            }
            return null;
        }
    }
}
