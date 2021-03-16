package com.example.tp3;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> tasks;
    private TheTaskInterface theTaskInterface;

    public TaskAdapter(List<Task> tasks, TheTaskInterface theTaskInterface) {
        this.tasks = tasks;
        this.theTaskInterface = theTaskInterface;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.nameTextView);
            imageView = (ImageView) view.findViewById(R.id.categoryImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View taskView = layoutInflater.inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final TaskAdapter theTaskAdapter = this;
        final Task theTask = tasks.get(position);
        Task task = tasks.get(position);
        holder.textView.setText(task.getName());
        String name = task.getCategory().name();
        if (name.equals("courses"))
            holder.imageView.setImageResource(R.drawable.courses);
        else if (name.equals("enfant"))
            holder.imageView.setImageResource(R.drawable.enfant);
        else if (name.equals("lecture"))
            holder.imageView.setImageResource(R.drawable.lecture);
        else if (name.equals("menage"))
            holder.imageView.setImageResource(R.drawable.menage);
        else if (name.equals("sport"))
            holder.imageView.setImageResource(R.drawable.sport);
        else if (name.equals("travail"))
            holder.imageView.setImageResource(R.drawable.travail);
        else
            holder.imageView.setImageResource(R.drawable.question);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task t= tasks.get(position);
                theTaskInterface.taskSelected(t);

//                Intent intent = new Intent(v.getContext(), DetailTaskActivity.class);
//                intent.putExtra("name",t.getName());
//                intent.putExtra("duration", t.getDuration());
//                intent.putExtra("description", t.getDescription());
//                intent.putExtra("category", t.getCategory().name());
//                v.getContext().startActivity(intent);
            }
        });
        /*
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext()); builder.setMessage("Are you sure about deleting this task?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("SUPR","ok on supprime réellement");
                                tasks.remove(theTask);
                                theTaskAdapter.notifyDataSetChanged();
                                Log.d("SUPR","nombre d'éléméents dans la liste : "+tasks.size()); }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) { Log.d("SUPR","ah? Alors on annule");
                            } });
                builder.create().show();

                return true;
            }
        });

        */
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
