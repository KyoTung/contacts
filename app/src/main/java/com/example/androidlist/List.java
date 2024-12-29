package com.example.androidlist;
import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class List<S> extends AppCompatActivity {

    private EditText name;
    private EditText phoneNumber;

    private Button button;

    private ListView listContacts;
    private ArrayList<String> data;

    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_handel_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        button = findViewById(R.id.button);
        listContacts = findViewById(R.id.listContacts);

        data = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>((Context) List.this, android.R.layout.simple_list_item_1, (java.util.List) data);

        listContacts.setAdapter(arrayAdapter);

        listContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }




    public void addPhone(View view) {
        String nameText = name.getText().toString();
        String phoneNum = phoneNumber.getText().toString();
        if(!nameText.isEmpty() || !phoneNum.isEmpty()){
            String contact =  nameText + "  " + phoneNum;
            data.add(contact);
            arrayAdapter.notifyDataSetChanged();
            name.setText("");
            phoneNumber.setText("");
        }
    }


}