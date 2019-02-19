package com.gacon.julien.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.SearchFragment;
import java.util.Calendar;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    // for the date
    private static final String TAG = "SearchActivity";
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerBeginDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerEndDate;

    // Design

    @BindView(R.id.fragment_base_search_notification_begin_date)
    TextView mDisplayBeginDate;
    @BindView(R.id.fragment_base_search_notification_end_date)
    TextView mDisplayEndDate;
    @BindView(R.id.fragment_base_search_edit_text)
    EditText mEditText;
    @BindView(R.id.fragment_base_search_notification_button)
    Button mSearchBtn;
    @BindView(R.id.fragment_base_search_notification_arts_check_box)
    CheckBox mCheckBoxArt;
    @BindView(R.id.fragment_base_search_notification_business_check_box)
    CheckBox mCheckBoxBusiness;
    @BindView(R.id.fragment_base_search_notification_entrepreneurs_check_box)
    CheckBox mCheckBoxEntrepreneurs;
    @BindView(R.id.fragment_base_search_notification_politics_check_box)
    CheckBox mCheckBoxPolitics;
    @BindView(R.id.fragment_base_search_notification_sports_check_box)
    CheckBox mCheckBoxSports;
    @BindView(R.id.fragment_base_search_notification_travel_check_box)
    CheckBox mCheckBoxTravel;

    // Data

    private String dateBegin;
    private String dateBeginForData;
    private String endDate;
    private String endDateForData;

    private int day;
    private int month;
    private int year;

    private String mQuery;
    private String mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchBtn.setEnabled(false);

        //Edit text
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchBtn.setEnabled(s.toString().length() != 0); }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        // Date
        mDisplayBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDisplay(mOnDateSetListenerBeginDate);
            }
        });

        mOnDateSetListenerBeginDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;

                dateBegin = dayOfMonth + "/" + month + "/" + year;

                dateBeginForData = dayOfMonth + "/" + month + "/" + year;
                if (month < 10) {
                    dateBeginForData = year + "0" + month + "" + dayOfMonth;
                } else dateBeginForData = year + "" + month + "" + dayOfMonth;

                mDisplayBeginDate.setText(dateBegin);
            }

        };

        mDisplayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDisplay(mOnDateSetListenerEndDate);

            }
        });

        mOnDateSetListenerEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                endDate = dayOfMonth + "/" + month + "/" + year;
                if (month < 10) {
                    endDateForData = year + "0" + month + "" + dayOfMonth;
                } else endDateForData = year + "" + month + "" + dayOfMonth;

                mDisplayEndDate.setText(endDate);
            }
        };


        // Search Button
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // CheckBox

                if (mCheckBoxArt.isChecked()) {
                    mFilter = "Arts";
                }
                if (mCheckBoxBusiness.isChecked()) {
                    mFilter = "Business";
                }
                if (mCheckBoxEntrepreneurs.isChecked()) {
                    mFilter = "Entrepreneurs";
                }
                if (mCheckBoxPolitics.isChecked()) {
                    mFilter = "Politics";
                }
                if (mCheckBoxSports.isChecked()) {
                    mFilter = "Sports";
                }
                if (mCheckBoxTravel.isChecked()) {
                    mFilter = "Travel";
                }

                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                
                mQuery = mEditText.getText().toString();

                Bundle queryData = new Bundle();
                queryData.putString("data", mQuery);
                searchFragment.setArguments(queryData);

                queryData.putString("dateBegin", dateBeginForData);
                searchFragment.setArguments(queryData);

                queryData.putString("endDate", endDateForData);
                searchFragment.setArguments(queryData);

                queryData.putString("filter", mFilter);
                searchFragment.setArguments(queryData);

                transaction.replace(R.id.fragmentLayout, searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    public void createDisplay(DatePickerDialog.OnDateSetListener dateSetListener){
        Calendar cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        DatePickerDialog dialog = new DatePickerDialog(
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener,
                day,month,year);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, -5);
        dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
