package com.rodrigodelcanto.people.activities.person;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hbb20.CountryCodePicker;
import com.rodrigodelcanto.mobile.utils.Toasts;
import com.rodrigodelcanto.people.R;
import com.rodrigodelcanto.people.activities.tools.DatePickerFragment;
import com.rodrigodelcanto.people.document.person.PPerson;
import com.rodrigodelcanto.people.document.person.Person;

import java.text.ParseException;

/**
 * Created by delkant on 5/25/17.
 */

public class PersonDialog {

    public static void showAddOrEditPerson(final Context context, final Person person) {
        final int LASTNAME_MIN_LENGHT_VALIDATION = 2;
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
                        String phoneCode = areaCodeView.getFullNumber();
                        String phoneNumber = phoneView.getText().toString();
                        EditText zipCodeView = (EditText) dialog.getCustomView().findViewById(R.id.zipCode);
                        String zipCode = zipCodeView.getText().toString();
                        final EditText birthdayView = (EditText) dialog.getCustomView().findViewById(R.id.birthday);
                        String birthday = birthdayView.getText().toString();

                        person.setFirstName(firstName);
                        person.setLastName(lastName);
                        person.setZipCode(zipCode);
                        person.setPhoneNumber(phoneNumber);
                        person.setPhoneCode(phoneCode);
                        person.setBirthday(birthday);
                        PPerson.save(person);
                    }
                }).build();

        final View positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        EditText firstNameView = (EditText) dialog.getCustomView().findViewById(R.id.firstname);
        EditText lastNameView = (EditText) dialog.getCustomView().findViewById(R.id.lastname);
        EditText zipCodeView = (EditText) dialog.getCustomView().findViewById(R.id.zipCode);
        EditText phoneView = (EditText) dialog.getCustomView().findViewById(R.id.phone);
        CountryCodePicker areaCodeView = (CountryCodePicker) dialog.getCustomView().findViewById(R.id.area_code);
        final EditText birthdayView = (EditText) dialog.getCustomView().findViewById(R.id.birthday);
        //birthdayView.setEnabled(false);
        birthdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setField(birthdayView);
                datePickerFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "datePicker");
            }
        });

        if (person.getFirstName() != null) {
            firstNameView.setText(person.getFirstName());
        }
        if (person.getLastName() != null) {
            lastNameView.setText(person.getLastName());
        }
        if (person.getPhoneCode() != null) {
            areaCodeView.setCountryForPhoneCode(Integer.valueOf(person.getPhoneCode()));
        }
        if (person.getPhoneNumber() != null) {
            phoneView.setText(person.getPhoneNumber());
        }
        if (person.getZipCode() != null) {
            zipCodeView.setText(person.getZipCode());
        }
        if (person.getBirthday() != null) {
            birthdayView.setText(person.getBirthday());
        }

        birthdayView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                positiveAction.setEnabled(validDate(s.toString()));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean valid = validDate(s.toString());
                if (valid) {
                    birthdayView.setError(null);
                } else {
                    birthdayView.setError("Invalid format. ie: 12/22/1976");

                }
                positiveAction.setEnabled(valid);
            }
        });


        lastNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                positiveAction.setEnabled(s.toString().trim().length() > LASTNAME_MIN_LENGHT_VALIDATION);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                positiveAction.setEnabled(s.toString().trim().length() > LASTNAME_MIN_LENGHT_VALIDATION);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().trim().length() > 2)) {
                    Toasts.showLong(context, context.getString(R.string.last_name) + " " + context.getString(R.string.too_short));
                }
            }
        });

        dialog.show();
        if (person.getLastName() == null || person.getLastName().length() < LASTNAME_MIN_LENGHT_VALIDATION) {

            positiveAction.setEnabled(false); // disabled by default
        }
    }

    private static final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");

    public static boolean validDate(String input) {
        if (input != null) {
            try {
                java.util.Date ret = sdf.parse(input.trim());
                if (sdf.format(ret).equals(input.trim())) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
