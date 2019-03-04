package com.gacon.julien.mynews.controllers.fragments.searchFragment;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.activities.ResultActivity;
import com.gacon.julien.mynews.controllers.utils.MyAlarmService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;
import static com.gacon.julien.mynews.controllers.utils.AppNotification.CHANNEL_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseSearchAndNotifFragment extends Fragment implements View.OnClickListener {

    protected abstract int getFragmentLayout();
    protected abstract boolean getNotificationVisibility();
    protected abstract boolean getButtonVisibility();

    public static final String QUERY = "query";
    public static final String DATE_BEGIN = "dateBegin";
    public static final String END_DATE = "endDate";
    public static final String FILTER = "filter";

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
    @BindView(R.id.fragment_base_search_notification_switch)
    Switch mNotificationSwitch;
    @BindView(R.id.fragment_base_search_notification_sports_check_box)
    CheckBox mCheckBoxSports;
    @BindView(R.id.fragment_base_search_notification_travel_check_box)
    CheckBox mCheckBoxTravel;

    // for the date
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerBeginDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerEndDate;
    private String dateBegin;
    String dateBeginForData;
    private String endDate;
    String endDateForData;

    String mQuery;
    String mFilter;
    MainSearchFragment.OnButtonClickedListener mCallback;

    //Alarm manager
    private PendingIntent pendingIntent;

    // Notifications
    private NotificationManagerCompat mNotificationManager;

    public BaseSearchAndNotifFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        mSearchBtn.setEnabled(false);

        boolean i = getNotificationVisibility();
        if (i) {
            mNotificationSwitch.setVisibility(View.VISIBLE);

        } else {
            mNotificationSwitch.setVisibility(View.VISIBLE);
        }

        // TODO : why btn won't disappear ?
        boolean j = getButtonVisibility();
        if (j) {
            mSearchBtn.setVisibility(View.VISIBLE);

        } else {
            mSearchBtn.setVisibility(View.GONE);
        }

        // Switch notification button

        // check current state of a Switch
        mNotificationSwitch.setTextOn("On");
        mNotificationSwitch.setTextOff("Off");

        mNotificationManager = NotificationManagerCompat.from(getContext());

        mNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.i("Switch_Notification", "Switch is on !");
                    getQuery();
                    getCheckBox();

                    // Alarm manager

                    Intent myIntent = new Intent(getContext(), MyAlarmService.class);
                    pendingIntent = PendingIntent.getService(getContext(), 0, myIntent, 0);
                    AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(ALARM_SERVICE);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.SECOND, 10);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    Toast.makeText(getContext(), "Start Alarm", Toast.LENGTH_LONG).show();

                    // Notification
                    Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("My notification")
                            .setContentText("Much longer text that cannot fit one line...")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Much longer text that cannot fit one line..."))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    mNotificationManager.notify(1, notification);

                } else Log.i("Switch_Notification", "Switch is off !");

                // TODO Auto-generated method stub
                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                Toast.makeText(getContext(), "Cancel!", Toast.LENGTH_LONG).show();
            }
        });


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
        getCheckBox();

        mCallback.onButtonClicked(v);

        Intent intentResultActivity = new Intent(getActivity(), ResultActivity.class);
        // put data into activity
        intentResultActivity.putExtra(QUERY, getQuery());
        intentResultActivity.putExtra(DATE_BEGIN, dateBeginForData);
        intentResultActivity.putExtra(END_DATE, endDateForData);
        intentResultActivity.putExtra(FILTER, mFilter);

        if (!mFilter.equals("")) {
            startActivity(intentResultActivity);
        }
    }

    private String getQuery() {
        mQuery = "";
        mQuery = mEditText.getText().toString();
        Log.i("Query", mQuery);
        return mQuery;
    }

    private void getCheckBox() {
        mFilter = "";

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

        if (mFilter.equals("")) {
            Toast toast = Toast.makeText(getContext(), "Veuillez séléctionner au moins une catégorie", Toast.LENGTH_SHORT);
            toast.show();
        }

        Log.i("Check_Box", mFilter);
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
