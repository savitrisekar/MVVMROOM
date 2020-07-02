package id.sekar.mvvmroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.sekar.mvvmroom.database.models.Note;
import id.sekar.mvvmroom.repositories.NotesRepository;

public class NotesListViewModel extends AndroidViewModel {

    private LiveData<List<Note>> listAllNotes;
    NotesRepository notesRepository;

    public NotesListViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        listAllNotes = notesRepository.getAllNotes();
    }

    public LiveData<List<Note>> getALlNotes() {
        return listAllNotes;
    }

    public void addNote(Note note) {
        notesRepository.addNote(note);
    }
}
