package id.sekar.mvvmroom.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import id.sekar.mvvmroom.R;
import id.sekar.mvvmroom.database.models.Note;
import id.sekar.mvvmroom.utils.DateConverter;

public class NotesAdapter extends RecyclerView.Adapter {

    //create list of notes
    List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get layout inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inflate layout
        View view = inflater.inflate(R.layout.item_note, parent, false);

        //returm mote holder and pass row inside
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//get current note
        Note note = notes.get(position);
        //cast notes holder
        NotesViewHolder notesViewHolder = (NotesViewHolder) holder;
        //set title desc and created at
        notesViewHolder.tvNoteTitle.setText(note.getNoteTitle());
        notesViewHolder.tvNoteDsc.setText(note.getNoteDescription());
        notesViewHolder.tvDate.setText(DateConverter.getDayMonth(note.getCreatedAt()));
        //create random color and set
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        notesViewHolder.flBackStrip.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView tvNoteTitle, tvNoteDsc, tvDate;
        FrameLayout flBackStrip;

        public NotesViewHolder(View view) {
            super(view);
            tvNoteTitle = view.findViewById(R.id.tv_note_title);
            tvNoteDsc = view.findViewById(R.id.tv_note_dsc);
            tvDate = view.findViewById(R.id.tv_note_date);
            flBackStrip = view.findViewById(R.id.fl_back_strip);
        }
    }

    public void addNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
}
