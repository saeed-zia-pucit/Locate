package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RemoveEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_employee);
        final EditText txt_remove=(EditText)findViewById(R.id.remove_text);
        Button remove=(Button)findViewById(R.id.remove_btn);
remove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        databaseHelper.deleteContact(new Contact("dummy",txt_remove.getText().toString()),"manager");
    }
});


    }
}
