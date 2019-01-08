package com.hfad.pizzasupplibraryandappbars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;
import android.support.v4.view.MenuItemCompat;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider sap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set this activity's app bar
        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(t);
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
}
