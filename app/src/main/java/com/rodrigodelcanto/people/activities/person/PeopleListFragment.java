package com.rodrigodelcanto.people.activities.person;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.couchbase.lite.util.Log;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.QPerson;

/**
 * Created by delkant
 */
public class PeopleListFragment extends Fragment {
    private static int DEFAULT_PHONE_COLS = 1;
    private static int DEFAULT_TABLET_COLS = 8;

    public static final String ARG_OBJ_TYPE = "ARG_OBJ_TYPE";
    public static final String ARG_STRING_VAL = "ARG_STRING_VAL";
    public static final String ARG_FORM_TYPE = "ARG_FORM_TYPE";
    public static final String ARG_FIELDS_TO_SHOW = "ARG_FIELDS_TO_SHOW";
    public static final String ARG_PHONE_CARD_COLS = "ARG_PHONE_CARD_COLS";
    public static final String ARG_TABLET_CARD_COLS = "ARG_TABLET_CARD_COLS";

    private StaggeredGridLayoutManager gridLayoutManager;
    private RecyclerView tabPageRecylerView;
    private PeopleAdapter mAdapter = null;
    private int mColumnsPhone;
    private int mColumnsTablet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_people_list, container, false);

        if (tabPageRecylerView == null) {
            tabPageRecylerView = (RecyclerView) view.findViewById(R.id.list_people);

            boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
            int columns = (mColumnsPhone == 0) ? DEFAULT_PHONE_COLS : mColumnsPhone;
            if (tabletSize) {
                columns = (mColumnsTablet == 0) ? DEFAULT_TABLET_COLS : mColumnsTablet;
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            }
            gridLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
            tabPageRecylerView.setHasFixedSize(true);
            tabPageRecylerView.setLayoutManager(gridLayoutManager);
        }

        mAdapter = new PeopleAdapter();
        QPerson.findAll(true, getActivity(), mAdapter);
        tabPageRecylerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ON_DESTROY", "on destroy TABFragment");
//        if (liveQuery != null) {
//            liveQuery.stop();
//        }
    }


}
