package com.harman.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harman.myapplication.MainActivity;
import com.harman.myapplication.R;
import com.harman.myapplication.entity.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formattable;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements Filterable {
    List<Todo> todoList;
    List<Todo> todolistfilter;

    public static final int Request = 1002;


    public TodoAdapter(List<Todo> todoList, MainActivity mainActivity) {
        this.todoList = todoList;
        this.todolistfilter = todoList;

    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoAdapter.ViewHolder viewHolder, final int i) {
        final Todo todo = todoList.get(i);
        viewHolder.TxtTitle.setText(todo.getTitle());
        viewHolder.TxtDescription.setText(todo.getDescription());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }



    @Override
    public int getItemCount() {
        return todolistfilter.size();
    }

    public void addItem(Todo todo) {
        todoList.add(todo);
        notifyDataSetChanged();
    }

    public void updateItems(List<Todo> list) {
        todoList = list;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    todolistfilter = todoList;
                } else {

                    ArrayList<Todo> filteredList = new ArrayList<>();

                    for (Todo todo : todoList) {

                        if (todo.getTitle().toLowerCase().contains(charString) || todo.getDescription().toLowerCase().contains(charString)) {

                            filteredList.add(todo);
                        }
                    }

                    todolistfilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = todolistfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                todolistfilter = (ArrayList<Todo>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView TxtTitle, TxtDescription;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtTitle = (TextView) itemView.findViewById(R.id.txttitle);
            TxtDescription = (TextView) itemView.findViewById(R.id.txtdesc);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }


}





