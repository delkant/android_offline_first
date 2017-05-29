package com.rodrigodelcanto.people.activities.person;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.PPerson;
import com.rodrigodelcanto.people.document.person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by delkant on 4/11/17.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PersonHolder> {
    List<Person> people;
    List<Person> selected = new ArrayList<>();
    AppCompatActivity activity;

    Menu mContextMenu;
    ActionMode mActionMode;
    boolean isMultiSelect = false;

    public PeopleAdapter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_person_item, parent, false);
        return new PersonHolder(this, view);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int pos) {
        if (people != null) {
            Person person = people.get(pos);
            holder.bind(person, selected.contains(person));
            holder.showSelectable(selectionSize() > 0);
            showContextActionMenu();
        }
    }

    @Override
    public int getItemCount() {
        return people != null ? people.size() : 0;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    void onSelect(Person person){
        selected.add(person);
        notifyDataSetChanged();
    }

    public int selectionSize(){
        return selected.size();
    }


    private void deleteSelected() {
        for(Person person: selected) {
            PPerson.delete(person);
            people.remove(person);
        }
        selected.clear();
    }

    private void showContextActionMenu(){
        isMultiSelect = true;

        if (mActionMode == null && selectionSize() > 0) {
            mActionMode = activity.startActionMode(mActionModeCallback);
        }

    }
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.multi_select_menu, menu);
            mContextMenu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    deleteSelected();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            selected.clear();
            notifyDataSetChanged();
        }
    };
}