package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.DATE_BEGIN;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.END_DATE;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.FILTER;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.QUERY;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseSearchAndNotifFragment extends Fragment implements View.OnClickListener {

    public BaseSearchAndNotifFragment() {
        // Required empty public constructor
    }

    protected abstract int getFragmentLayout();
    protected abstract boolean getNotificationVisibility();
    protected abstract boolean getButtonVisibility();

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
}
