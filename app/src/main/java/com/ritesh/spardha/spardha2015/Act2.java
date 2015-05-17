package com.ritesh.spardha.spardha2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.ritesh.spardha.ContactFunctions.ContactsHelper;
import com.ritesh.spardha.adapters.CategoriesGridAdapter;


public class Act2 extends ActionBarActivity {

    ContactsHelper contactsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_grid_layout);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        String[] catArray = getResources().getStringArray(R.array.categories_array);
        CategoriesGridAdapter categoriesGridAdapter = new CategoriesGridAdapter(this, catArray, R.drawable.bkg1);
        gridView.setAdapter(categoriesGridAdapter);
        // setContentView(R.layout.single_contact_layout);
        // contactsHelper = new ContactsHelper("+918953839075", "Ritesh Kumar", this);
    }


    public void onClickCall(View v) {
        contactsHelper.makeCall();
    }

    public void onClickAddToContacts(View v) {
        contactsHelper.insertContact();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
