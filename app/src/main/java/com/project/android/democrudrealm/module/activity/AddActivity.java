package com.project.android.democrudrealm.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.android.democrudrealm.R;
import com.project.android.democrudrealm.module.helper.RealmHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {


    @BindView(R.id.inputTitle)
    EditText mInputTitle;
    @BindView(R.id.inputDescription)
    EditText mInputDesc;
    @BindView(R.id.btn_save)
    Button mBtnSave;

    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        realmHelper = new RealmHelper(this);
    }

    @OnClick(R.id.btn_save)
    public void onClick(View view) {
        String title, description;
        title = mInputTitle.getText().toString();
        description = mInputDesc.getText().toString();
        realmHelper.add(title, description);

        Toast.makeText(this, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
        mInputTitle.setText("");
        mInputDesc.setText("");
    }

}

