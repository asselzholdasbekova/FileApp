package kz.assel.techtask.FileApp.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "File_Upload")
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_date")
    private Date date;

    @Column(name = "file_path")
    private String path;

    public FileUpload(){}

    public FileUpload(String name, Date date, String path) {
        this.name = name;
        this.date = date;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
