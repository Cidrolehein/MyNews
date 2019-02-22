package com.gacon.julien.mynews.controllers.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.activities.SearchActivity;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSearchFragment extends Fragment implements View.OnClickListener {

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

    private FragmentActivity myContext;


    public MainSearchFragment() {
        // Required empty public constructor
    }

    private OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_search, container, false);
        ButterKnife.bind(this, view);
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

        mSearchBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

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

        mQuery = mEditText.getText().toString();
        SearchResultFragment searchResultFragment = new SearchResultFragment();

        Bundle queryData = new Bundle();
        queryData.putString("data", mQuery);
        searchResultFragment.setArguments(queryData);

        queryData.putString("dateBegin", dateBeginForData);
        searchResultFragment.setArguments(queryData);

        queryData.putString("endDate", endDateForData);
        searchResultFragment.setArguments(queryData);

        queryData.putString("filter", mFilter);
        searchResultFragment.setArguments(queryData);

        mCallback.onButtonClicked(v);

    }

    private void createCallbackToParentActivity(){
        try {
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ "must implement OnButtonClicketListener");
        }
    }

    public void createDisplay(DatePickerDialog.OnDateSetListener dateSetListener){
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(
                Objects.requireNonNull(getActivity()),
                dateSetListener,
                day,month,year);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, -5);
        dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        dialog.show();
    }

}
