package com.reyhandigitalworld.catatanapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reyhandigitalworld.catatanapp.R;
import com.reyhandigitalworld.catatanapp.room.NoteEntity;
import com.reyhandigitalworld.catatanapp.ui.activity.ReadActivity;

import java.util.ArrayList;

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.ViewHolder> {

    private ArrayList<NoteEntity> noteEntities;
    private Context context;

    public ListNoteAdapter(ArrayList<NoteEntity> noteEntities, Context context) {
        this.noteEntities = noteEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(noteEntities.get(position).getTitle());
        holder.tvNote.setText(noteEntities.get(position).getNote());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadActivity.class);
            intent.putExtra("id", noteEntities.get(position).getId());
            intent.putExtra("judul", noteEntities.get(position).getTitle());
            intent.putExtra("note", noteEntities.get(position).getNote());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvNote;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title);
            tvNote = itemView.findViewById(R.id.note);
        }
    }
}
