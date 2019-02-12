package com.gacon.julien.mynews.controllers.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.MostPopularArticleFragment;
import com.gacon.julien.mynews.controllers.fragments.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mSearchBtn;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEditText = (EditText) findViewById(R.id.fragment_base_search_edit_text);
        mSearchBtn = (Button) findViewById(R.id.fragment_base_search_notification_button);

        mSearchBtn.setEnabled(false);

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

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentLayout, searchFragment);
                transaction.commit();
                mQuery = mEditText.getText().toString();
                Log.v("Test", mQuery);

            }
        });

    }

}
