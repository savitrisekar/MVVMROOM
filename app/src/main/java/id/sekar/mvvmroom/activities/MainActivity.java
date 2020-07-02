package id.sekar.mvvmroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.sekar.mvvmroom.R;
import id.sekar.mvvmroom.adapters.NotesAdapter;
import id.sekar.mvvmroom.database.models.Note;
import id.sekar.mvvmroom.utils.Space;
import id.sekar.mvvmroom.viewmodel.NotesListViewModel;

public class MainActivity extends AppCompatActivity {

    NotesListViewModel notesListViewModel;
    FloatingActionButton fabAdd;
    RecyclerView rvAdd;
    NotesAdapter adapter;
    EditText edtTitle, edtDsc;
    TextView tvAdd, tvCancel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        setupRecycler();
        setupAdapter();
        setupViewModel();
    }

    private void setupViewModel() {
        notesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        notesListViewModel.getALlNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addNotes(notes);
            }
        });
    }

    private void initView() {
        fabAdd = findViewById(R.id.fab_add);
        rvAdd = findViewById(R.id.rv_note);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void setupRecycler() {
        rvAdd.setLayoutManager(new LinearLayoutManager(this));
        rvAdd.addItemDecoration(new Space(20));
    }

    private void setupAdapter() {
        adapter = new NotesAdapter();
        rvAdd.setAdapter(adapter);
    }

    private void showDialog() {
        fabAdd.hide();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_note_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        edtTitle = dialog.findViewById(R.id.edt_input_title);
        edtDsc = dialog.findViewById(R.id.edt_input_dsc);
        tvAdd = dialog.findViewById(R.id.tv_add);
        tvCancel = dialog.findViewById(R.id.tv_cancel);

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String dsc = edtDsc.getText().toString();
                Date date = Calendar.getInstance().getTime();
                //add note
                notesListViewModel.addNote(new Note(title, dsc, date));
                fabAdd.show();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                fabAdd.show();
            }
        });
        dialog.show();
    }
}
