package com.rodrigodelcanto.people.activities.person;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hbb20.CountryCodePicker;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.document.person.CUDPerson;
import com.rodrigodelcanto.people.document.person.Person;

/**
 * Created by delkant on 5/25/17.
 */

public class PersonDialog {

    public static void showAddOrEditPerson(final Context context, final Person person) {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.Person)
                .canceledOnTouchOutside(false)
                .customView(R.layout.person_dialog_person_form, true)
                .positiveText(R.string.Save)
                .negativeText(R.string.Cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        EditText firstNameView = (EditText) dialog.getCustomView().findViewById(R.id.firstname);
                        String firstName = firstNameView.getText().toString();
                        EditText lastNameView = (EditText) dialog.getCustomView().findViewById(R.id.lastname);
                        String lastName = lastNameView.getText().toString();
                        CountryCodePicker areaCodeView = (CountryCodePicker) dialog.getCustomView().findViewById(R.id.area_code);
                        EditText phoneView = (EditText) dialog.getCustomView().findViewById(R.id.phone);
                        String countryCode = areaCodeView.getFullNumberWithPlus();
                        String phone = phoneView.getText().toString();
                        String nationallityCode = areaCodeView.getSelectedCountryNameCode();
                        EditText zipCodeView =  (EditText) dialog.getCustomView().findViewById(R.id.zipCode);
                        String zipCode = zipCodeView.getText().toString();

                        person.setFirstName(firstName);
                        person.setLastName(lastName);
                        person.setZipCode(zipCode);
                        person.setPhoneNumber(phone);
                        CUDPerson.create(person);

                    }
                }).build();

        final View positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        EditText firstName = (EditText) dialog.getCustomView().findViewById(R.id.firstname);
        EditText lastName = (EditText) dialog.getCustomView().findViewById(R.id.lastname);


        if(person.getFirstName()!=null){
            firstName.setText(person.getFirstName());
        }
        if(person.getLastName()!=null){
            lastName.setText(person.getLastName());
        }
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                positiveAction.setEnabled(s.toString().trim().length() > 2);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                positiveAction.setEnabled(s.toString().trim().length() > 2);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default
    }

}
