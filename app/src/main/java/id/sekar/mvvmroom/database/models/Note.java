package id.sekar.mvvmroom.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import id.sekar.mvvmroom.utils.DateConverter;

@Entity
public class Note {

    //room database entity primary key
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String noteTitle;
    private String noteDescription;
    //converter for date
    @TypeConverters(DateConverter.class)
    private Date createdAt;

    public Note(String noteTitle, String noteDescription, Date createdAt) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
