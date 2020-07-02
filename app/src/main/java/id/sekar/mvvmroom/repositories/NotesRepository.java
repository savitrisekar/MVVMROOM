package id.sekar.mvvmroom.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.sekar.mvvmroom.database.NoteDatabase;
import id.sekar.mvvmroom.database.dao.INoteDao;
import id.sekar.mvvmroom.database.models.Note;

// Notes repository
public class NotesRepository {

    // Live data of list of all notes
    private LiveData<List<Note>> listAllNotes;
    // Define notes dao
    INoteDao iNoteDao;

    public NotesRepository(@NonNull Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        //init note dao
        iNoteDao = noteDatabase.iNoteDao();
        //get all note
        listAllNotes = iNoteDao.getAllNotes();
    }

    //method to get all note
    public LiveData<List<Note>> getAllNotes() {
        return listAllNotes;
    }

    //method add note
    public void addNote(Note note) {
        new AddNote().execute(note);
    }

    private class AddNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            iNoteDao.insertNote(notes[0]);
            return null;
        }
    }
}
