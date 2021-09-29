package com.example.insert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  Button add;
    private  ListView listView;
    private  TextView count;
    Context context;
    private List<ToDo> toDos;

    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add =findViewById(R.id.btn1);
        listView = findViewById(R.id.listView1);
        count = findViewById(R.id.tv3);
        context = this;
        toDos =  new ArrayList<>();

        dbHandler = new DbHandler(context);
        toDos = dbHandler.getAllToDos() ;

        ToDoAdapter toDoAdapter = new ToDoAdapter(context,R.layout.single_todo,toDos);
        listView.setAdapter(toDoAdapter);

        int countToDo = dbHandler.countToDo();
        count.setText("You Have "+countToDo+" todos");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddTodo.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ToDo todo = toDos.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getTitle());
                builder.setMessage(todo.getDescrpition());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            todo.setFinished(System.currentTimeMillis());
                            dbHandler.updateSingletToDo(todo);
                            startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {
                           dbHandler.deleteToDO(todo.getId());
                           startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context,EditToDO.class);
                        intent.putExtra("id",String.valueOf(todo.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}