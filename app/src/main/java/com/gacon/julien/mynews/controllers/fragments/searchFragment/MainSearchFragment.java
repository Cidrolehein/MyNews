package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.activities.ResultActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSearchFragment extends Fragment implements View.OnClickListener {

    public static final String QUERY = "query";
    public static final String DATE_BEGIN = "dateBegin";

    // Design
    public static final String END_DATE = "endDate";
    public static final String FILTER = "filter";
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

    // Data
    @BindView(R.id.fragment_base_search_notification_sports_check_box)
    CheckBox mCheckBoxSports;
    @BindView(R.id.fragment_base_search_notification_travel_check_box)
    CheckBox mCheckBoxTravel;
    // for the date
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerBeginDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerEndDate;
    private String dateBegin;
    private String dateBeginForData;
    private String endDate;
    private String endDateForData;
    private String mFilter = null;
    private OnButtonClickedListener mCallback;

    public MainSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_search, container, false);
        ButterKnife.bind(this, view);
        mSearchBtn.setEnabled(false);

        //Edit text
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchBtn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Date of begin
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

        // End of date

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

                System.out.println(endDateForData);

                // Compare begin and end of date
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

               if (dateBegin != null) {
                   try {
                       Date bDate = sdf.parse(dateBegin);
                       Date eDate = sdf.parse(endDate);
                       if (eDate.before(bDate)) {
                           Toast toast = Toast.makeText(getContext(), "La date de fin doit être postérieure à la date de début", Toast.LENGTH_SHORT);
                           toast.show();
                       } else {
                           mDisplayEndDate.setText(endDate);
                       }
                   } catch (ParseException e) {
                       e.printStackTrace();
                   }
               } else {
                   mDisplayEndDate.setText(endDate);
               }

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

    // TODO : check default condition mFilter = null

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

        String query = mEditText.getText().toString();

        mCallback.onButtonClicked(v);

        // put data into bundle
        Intent intentResultActivity = new Intent(getActivity(), ResultActivity.class);
        intentResultActivity.putExtra(QUERY, query);
        intentResultActivity.putExtra(DATE_BEGIN, dateBeginForData);
        intentResultActivity.putExtra(END_DATE, endDateForData);
        intentResultActivity.putExtra(FILTER, mFilter);

        if (mFilter != null) {
            startActivity(intentResultActivity);
        } else {
            Toast toast = Toast.makeText(getContext(), "Veuillez séléctionner au moins une catégorie", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void createCallbackToParentActivity() {
        try {
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString() + "must implement OnButtonClicketListener");
        }
    }

    public void createDisplay(DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(
                Objects.requireNonNull(getActivity()),
                dateSetListener,
                day, month, year);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, -5);
        dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        dialog.show();
    }

    public interface OnButtonClickedListener {
        void onButtonClicked(View view);
    }

}
