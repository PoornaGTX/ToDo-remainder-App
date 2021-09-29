package com.example.insert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {

    private Context context;
    private int resourse;
    List<ToDo> todos;

    ToDoAdapter(Context context, int resourse, List<ToDo> toDos){
        super(context,resourse,toDos);
        this.context=context;
        this.resourse=resourse;
        this.todos=toDos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resourse,parent,false);

        TextView title =row.findViewById(R.id.titletv);
        TextView  decription = row.findViewById(R.id.decrptv);
        ImageView imageView = row.findViewById(R.id.imagev);


        ToDo toDo = todos.get(position);
        title.setText(toDo.getTitle());
        decription.setText(toDo.getDescrpition());
        imageView.setVisibility(row.INVISIBLE);

        if(toDo.getFinished() > 0){

            imageView.setVisibility(View.VISIBLE);

        }
        return row;



    }
}
