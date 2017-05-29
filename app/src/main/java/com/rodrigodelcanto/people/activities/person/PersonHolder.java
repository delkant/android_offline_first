package com.rodrigodelcanto.people.activities.person;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.Person;

/**
 * Created by delkant on 4/11/17.
 */

public class PersonHolder extends RecyclerView.ViewHolder {
    private final PeopleAdapter adapter;
    private final TextView personFullNameTxtView;
    private final AppCompatCheckBox personChecboxSelected;
    private final LinearLayout personSelection;
    private final TextView person2ndLineTxtView;
    private final TextView person3rdLineTxtView;
    //private final RoundedImageView imageView;
    private Person person;
    private View.OnLongClickListener longClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            adapter.onSelect(PersonHolder.this.person);
            return false;
        }
    };
    private View.OnClickListener shortClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PersonDialog.showAddOrEditPerson(adapter.activity, PersonHolder.this.person);
        }
    };

    public PersonHolder(PeopleAdapter adapter, View itemView) {
        super(itemView);
        this.adapter = adapter;
        personFullNameTxtView = (TextView) itemView.findViewById(R.id.card_title);
        person2ndLineTxtView = (TextView) itemView.findViewById(R.id.card_subtitle);
        person3rdLineTxtView = (TextView) itemView.findViewById(R.id.card_time);
        personSelection = (LinearLayout) itemView.findViewById(R.id.card_selection);
        personChecboxSelected = (AppCompatCheckBox) itemView.findViewById(R.id.card_select_checkbox);
    }

    public void bind(Person person, boolean isSelected) {
        this.person = person;
        personFullNameTxtView.setText(person.getFullName());
        person2ndLineTxtView.setText("Phone: +" + person.getPhoneCode() + person.getPhoneNumber() +" | Zip: "+person.getZipCode());
        person3rdLineTxtView.setText(person.getBirthday());
        personChecboxSelected.setChecked(isSelected);
        itemView.setOnLongClickListener(longClick);
        itemView.setOnClickListener(shortClick);

    }

    public void showSelectable(boolean show) {
        personSelection.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
