package id.sekar.mvvmroom.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

import id.sekar.mvvmroom.database.models.Note;
import id.sekar.mvvmroom.utils.DateConverter;

import static androidx.room.OnConflictStrategy.REPLACE;

//data access obj
@Dao
@TypeConverters(DateConverter.class)
public interface INoteDao {

    //Dao method get all notes
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    //insert
    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    //delete
    @Delete
    void deleteNote(Note note);
}
