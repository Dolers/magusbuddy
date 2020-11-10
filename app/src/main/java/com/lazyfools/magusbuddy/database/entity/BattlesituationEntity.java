package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Battlesituations")
public class BattlesituationEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "ke")
    private String _ke;

    @ColumnInfo(name = "te")
    private String _te;

    @ColumnInfo(name = "ve")
    private String _ve;

    @ColumnInfo(name = "ce")
    private String _ce;

    @ColumnInfo(name = "description")
    private String _description;

    public BattlesituationEntity(String name, String ke, String te, String ve, String ce, String description) {
        _name = name;
        _ke = ke;
        _te = te;
        _ve = ve;
        _ce = ce;
        _description = description;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getKe() {
        return _ke;
    }

    public void setKe(String ke) {
        _ke = ke;
    }

    public String getTe() {
        return _te;
    }

    public void setTe(String te) {
        _te = te;
    }

    public String getVe() {
        return _ve;
    }

    public void setVe(String ve) {
        _ve = ve;
    }

    public String getCe() {
        return _ce;
    }

    public void setCe(String ce) {
        _ce = ce;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
