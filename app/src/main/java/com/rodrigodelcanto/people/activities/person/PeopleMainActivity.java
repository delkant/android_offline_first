package com.rodrigodelcanto.people.activities.person;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.Person;


public class PeopleMainActivity extends AppCompatActivity {

    private static Toolbar toolbar;
    private SmartTabLayout tabsLayout;
    private ViewPager viewPager;
    private FloatingActionButton newReceptionBtn;
    private FrameLayout frameNewReceptionBtn;
    //protected ViewPager.OnPageChangeListener tabChangeListener = newTabChangeListener();
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

    private void addPage(FragmentPagerItems pages, String tabName) {
        Bundle argForPersonClass = getArgsForPersonClass();
        pages.add(FragmentPagerItem.of(tabName, PeopleListFragment.class, argForPersonClass));
    }

    protected Bundle getArgsForPersonClass() {
        Bundle argDefault = new Bundle();
        argDefault.putSerializable(PeopleListFragment.ARG_OBJ_TYPE, Person.class);
//    argDefault.putInt(TicketsMainBoxFragment.ARG_PHONE_CARD_COLS, 1);
//    argDefault.putInt(TicketsMainBoxFragment.ARG_TABLET_CARD_COLS, 2);
//    argDefault.putString(TicketsMainBoxFragment.ARG_BOX_TYPE, box);

        return argDefault;
    }

    public SmartTabLayout getTabsLayout() {
        return tabsLayout;
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
//        getMenuInflater().inflate(R.menu.order_menu, menu);
//        MenuItem menuItem = menu.findItem(R.id.order_action_home);
//        menuItem.setActionView(getGeneralTabIcon(true));

        return true;
    }


    private ViewPager.OnPageChangeListener newTabChangeListener() {
//    return new ViewPager.SimpleOnPageChangeListener() {
//      @Override
//      public void onPageSelected(int position) {
//        if(boxes.get(position) == TicketBox.RECEPTION) {
//          frameNewReceptionBtn.setVisibility(View.VISIBLE);
//        } else {
//          frameNewReceptionBtn.setVisibility(View.GONE);
//        }
//      }
//    };
        return null;
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
        //PrimaryDrawerItem locationDrawerItem = new PrimaryDrawerItem().withName(R.string.Location);
        //final String fullName = Application.getPersistence().getPrincipal();
        PrimaryDrawerItem currentUser = new PrimaryDrawerItem().withName("Rodrigo Del Canto").withDescription("I am delkant").withIcon(R.mipmap.ic_launcher).withSelectable(false).withIsExpanded(false);

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
