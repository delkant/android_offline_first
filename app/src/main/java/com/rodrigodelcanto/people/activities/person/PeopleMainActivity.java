package com.rodrigodelcanto.people.activities.person;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.Person;


public class PeopleMainActivity extends AppCompatActivity {

    private static Toolbar toolbar;
    private FloatingActionButton fabAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.person_people_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fabAddPerson = (FloatingActionButton) findViewById(R.id.fab_new_person);
        fabAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDialog.showAddOrEditPerson(PeopleMainActivity.this, Person.newEntity());
            }
        });

        initLeftMenuDrawer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initLeftMenuDrawer() {
        PrimaryDrawerItem currentUser = new PrimaryDrawerItem().withName("Rodrigo Del Canto").withDescription("delkant").withIcon(R.mipmap.ic_launcher).withSelectable(false).withIsExpanded(false);

        DrawerBuilder drawerBuilder = new DrawerBuilder();


        drawerBuilder.withActivity(PeopleMainActivity.this)
                .addDrawerItems(currentUser)
                .withHeaderDivider(false)
                .withRootView(R.id.drawer_layout)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true);

        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                boolean drawerOpened = false;
                String name = ((Nameable) drawerItem).getName().getText(PeopleMainActivity.this);
                return drawerOpened;
            }
        }).build();
    }

}
