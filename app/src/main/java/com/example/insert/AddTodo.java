package com.example.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    //views
    private EditText title,desptn ;
    private Button add;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //link views
        title=findViewById(R.id.et4);
        desptn =findViewById(R.id.et5);
        add = findViewById(R.id.btn4);
        context =this;

        dbHandler = new DbHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTitle = title.getText().toString();
                String userDescr = desptn.getText().toString();
                long started = System.currentTimeMillis();

                ToDo todo = new ToDo(userTitle,userDescr,started,0);
                dbHandler.addToDo(todo);

                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }
}