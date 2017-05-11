package com.example.gsh.enote;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    private NoteDB mNoteDB;
    private SQLiteDatabase mSQLiteDatabase;
    private Button textbtn;
    private Button imgbtn;
    private Button videobtn;
    private ListView lv;
    private Intent intent;
    private MyAdapter mMyAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteDB=new NoteDB(this);
        mSQLiteDatabase=mNoteDB.getWritableDatabase();
        //addDB();
        initView();
    }

    private void initView() {
        lv= (ListView) findViewById(R.id.list);
        textbtn= (Button) findViewById(R.id.text);
        imgbtn= (Button) findViewById(R.id.img);
        videobtn= (Button) findViewById(R.id.video);
        textbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("flag","2");
                startActivity(intent);
            }
        });
        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("flag","3");
                startActivity(intent);
            }
        });
    }
    public void selectDB() {
        cursor = mSQLiteDatabase.query(NoteDB.TABLE_NAME, null, null, null, null,
                null, null);
        mMyAdapter = new MyAdapter(this, cursor);
        lv.setAdapter(mMyAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

}




