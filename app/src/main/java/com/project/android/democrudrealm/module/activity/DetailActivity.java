package com.project.android.democrudrealm.module.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.android.democrudrealm.R;
import com.project.android.democrudrealm.module.helper.RealmHelper;
import com.project.android.democrudrealm.module.model.DataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    private int position;
    private String title, description;
    private String intentTitle, intentDesc;
    private RealmHelper realmHelper;
    private ArrayList<DataModel> dataModels;

    @BindView(R.id.inputTitle)
    EditText mInputTitle;
    @BindView(R.id.inputDescription)
    EditText mInputDesc;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        realmHelper = new RealmHelper(this);
        dataModels = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);
        intentTitle = getIntent().getStringExtra("title");
        intentDesc = getIntent().getStringExtra("description");

        mInputTitle.setText(intentTitle);
        mInputDesc.setText(intentDesc);
    }

    @OnClick({R.id.btn_update, R.id.btn_delete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_delete:
                delete();
                break;
        }
    }
    private void delete(){
        realmHelper.delete(position);
        mInputTitle.setText("");
        mInputDesc.setText("");
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }

    private void update(){
        title = mInputTitle.getText().toString();
        description =mInputDesc.getText().toString();
        realmHelper.update(position, title, description);
        mInputTitle.setText("");
        mInputDesc.setText("");
        Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
    }
}
