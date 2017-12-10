package com.example.admin.easywallet;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.easywallet.adapter.myListAdapter;
import com.example.admin.easywallet.db.DatabaseHelper;
import com.example.admin.easywallet.model.WalletItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<WalletItem> mWalletItemList = new ArrayList<>();
    private myListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new myListAdapter(
                this,
                R.layout.item,
                mWalletItemList
        );

        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(mAdapter);

        Button mIncomeButton = findViewById(R.id.button_income);
        Button mExpressButon = findViewById(R.id.button_express);

        mIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddWalletActivity.class);
                startActivity(intent);
            }
        });

        mExpressButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddWalletActivityExpress.class);
                startActivity(intent);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WalletItem item = mWalletItemList.get(i);
                int phoneId = item.id;

                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ยืนยันลบรายการ'จ่ายค่าหอ "+item.money+" บาท'?");


                mDb.delete(
                        DatabaseHelper.TABLE_NAME,
                        DatabaseHelper.COL_ID + "=?",
                        new String[]{String.valueOf(phoneId)}
                );
                loadDataFromDb();
                mAdapter.notifyDataSetChanged();

            }
        });

    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                DatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mWalletItemList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MONEY));
            String picture = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PICTURE));

            WalletItem item = new WalletItem(id, title, number, picture);
            mWalletItemList.add(item);
        }
    }
}
