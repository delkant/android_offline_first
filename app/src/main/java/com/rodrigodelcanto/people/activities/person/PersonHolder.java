package com.rodrigodelcanto.people.activities.person;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.Person;

/**
 * Created by delkant on 4/11/17.
 */

public class PersonHolder extends RecyclerView.ViewHolder {

  private final TextView personFullNameTxtView;
  //private final RoundedImageView imageView;
  private Person person;

  public PersonHolder(View itemView) {
    super(itemView);
    personFullNameTxtView = (TextView) itemView.findViewById(R.id.card_title);

  }

  public void bind(Person person) {
    this.person = person;
    personFullNameTxtView.setText(person.getFullName());
    itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        return false;
      }
    });
  }
}
