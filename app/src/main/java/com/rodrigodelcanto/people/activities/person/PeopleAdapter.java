package com.rodrigodelcanto.people.activities.person;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.Person;

import java.util.List;

/**
 * Created by delkant on 4/11/17.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PersonHolder> {
    List<Person> people;

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_person_item, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int pos) {
        if (people != null) {
            Person person = people.get(pos);
            holder.bind(person);
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
}