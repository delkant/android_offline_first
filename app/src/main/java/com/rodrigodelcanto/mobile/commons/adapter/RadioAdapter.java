package com.rodrigodelcanto.mobile.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import com.rodrigodelcanto.people.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by delkant on 9/4/16.
 */

public class RadioAdapter extends BaseAdapter {
  final ViewHolder holder;
  final Context context;
  List<EntryData> entries = new ArrayList<EntryData>();
  private EntryData selected = new EntryData();

  public RadioAdapter(Context context) {
    this(context, null);
  }

  public RadioAdapter(Context context, List<EntryData> entries) {
    this.holder = new ViewHolder();
    this.context = context;
    this.entries = entries;
  }

  public String getSelectedName() {
    return selected.getName();
  }

  public String getSelectedShortName() {
    return selected.getShortName();
  }

  public String getSelectedId() {
    return selected.getId();
  }

  public Map<String, Object> getSelectedProperties() {
    return selected.getProperties();
  }

  @Override
  public int getCount() {
    return entries.size();
  }

  @Override
  public Object getItem(int position) {
    return entries.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, final ViewGroup parent) {

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.lgn_login_host_radio, null);

      final EntryData option = (EntryData) getItem(position);

      final String id = option.getId();
      final String name = option.getName();
      final String shortName = option.getShortName();
      final Map<String, Object> properties = option.getProperties();
      final boolean editable = option.isEditable();

      final RadioButton radioOpt = (RadioButton) convertView.findViewById(R.id.radio_entry_btn);
      final EditText radioEditText = (EditText) convertView.findViewById(R.id.radio_entry_editable_txt);
      if (editable) {
        radioEditText.setVisibility(View.VISIBLE);
      }
      radioOpt.setText(EntryData.getLabel(name, shortName));
      radioOpt.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
          InputMethodManager imm = (InputMethodManager) RadioAdapter.this.context.getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
          return false;
        }
      });

      radioOpt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton v, boolean isChecked) {
          if (isChecked) {
            int count = 0;
            for (RadioButton cb : holder.radioOptions) {
              cb.setChecked(false);
              if (position == count) {
                cb.setChecked(true);
                selected.id = id;
                selected.properties = properties;
                selected.name = name;
                selected.shortName = shortName;
              }
              count++;
            }
          }
        }
      });
      holder.radioOptions.add(radioOpt);
    }

    return convertView;
  }

  private class ViewHolder {
    List<RadioButton> radioOptions = new ArrayList<RadioButton>();
  }
}
