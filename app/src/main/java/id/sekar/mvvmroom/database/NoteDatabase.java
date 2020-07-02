package id.sekar.mvvmroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.sekar.mvvmroom.database.dao.INoteDao;
import id.sekar.mvvmroom.database.models.Note;

// Room database
@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    //define static instance
    private static NoteDatabase noteDatabase;

    // method get room
    public static NoteDatabase getDatabase(Context context) {
        if (noteDatabase == null)
            noteDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "notes_db")
                    .build();

        return noteDatabase;
    }

    //method remove
    public static void closeDatabase() {
        noteDatabase = null;
    }

    //define note dao
    public abstract INoteDao iNoteDao();
}
