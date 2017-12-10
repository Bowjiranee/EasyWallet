package com.example.admin.easywallet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.easywallet.db.DatabaseHelper;
import com.example.admin.easywallet.model.WalletItem;

import java.io.File;
import java.io.IOException;

public class AddWalletActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mWalletTitleEditText;
    private EditText mWalletMoneyEditText;
    private ImageView mIncomeImageView;
    private Button mSaveButton;

    private File mSelectedPictureFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);

        mWalletTitleEditText = findViewById(R.id.editText_details);
        mWalletMoneyEditText = findViewById(R.id.editText_money);
        mIncomeImageView = findViewById(R.id.imageView_Add_pic);
        mSaveButton = findViewById(R.id.save_button);

        mIncomeImageView.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
    }
}
