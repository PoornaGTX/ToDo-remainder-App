package com.example.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditToDO extends AppCompatActivity {

    private Button update;
    private EditText etext4,etext5 ;
    private DbHandler dbHandler;
    private Context context;
    private long updatedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        etext4=findViewById(R.id.et4);
        etext5=findViewById(R.id.et5);
        update = findViewById(R.id.btn4);
        context=this;
        dbHandler = new DbHandler(context);


        final String id =getIntent().getStringExtra("id");

        ToDo toDo = dbHandler.getSingaleToDo(Integer.parseInt(id));
        etext4.setText(toDo.getTitle());
        etext5.setText(toDo.getDescrpition());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etext4.getText().toString();
                String titleDescription = etext5.getText().toString();
                updatedDate = System.currentTimeMillis();

                ToDo todo = new ToDo(Integer.parseInt(id),title,titleDescription,updatedDate,0);

                int stae = dbHandler.updateSingletToDo(todo);
                System.out.println(stae);
                startActivity(new Intent(context,MainActivity.class));
                Toast.makeText(context, "Update Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}