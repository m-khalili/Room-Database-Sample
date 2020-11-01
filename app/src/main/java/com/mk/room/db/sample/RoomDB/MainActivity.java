package com.mk.room.db.sample.RoomDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.mk.room.db.sample.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText text;
    RecyclerView recycler;
    List<MainData> mainDataList = new ArrayList<>();
    RecyclerAdapter adapter;
    RoomDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        db = RoomDB.getInstance(this);
        mainDataList = db.mainDoa().getAll();

       adapter = new RecyclerAdapter(this , mainDataList);
        recycler.setAdapter(adapter);

    }

    private void bind() {
        text = findViewById(R.id.text);
        recycler = findViewById(R.id.recycler);
        findViewById(R.id.add).setOnClickListener(V->{

            String sText = text.getText().toString().trim();

            if (!sText.equals("")) {
                MainData data = new MainData();
                data.setText(sText);
                db.mainDoa().insert(data);
                text.setText("");
                mainDataList.clear();
                mainDataList.addAll(db.mainDoa().getAll());
                adapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.removeAll).setOnClickListener(V->{
            db.mainDoa().deleteAll(mainDataList);
            mainDataList.clear();
            mainDataList.addAll(db.mainDoa().getAll());
            adapter.notifyDataSetChanged();
        });
    }


}