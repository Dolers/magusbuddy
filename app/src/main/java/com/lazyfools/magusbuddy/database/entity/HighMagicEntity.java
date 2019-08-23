package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "magasmagia")
public class HighMagicEntity {
    public enum TypeEnum{
        ELEMI,
        TERMÉSZETI,
        TER,
        ASZTRAL,
        MENTAL,
        RUNA,
        IDO,
        NEKROMANCIA,
        DEMONOLOGIA,
        SZIMPATIKUS,
        EGYEB
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "nev")
    private String _nev;

    @ColumnInfo(name = "tipus")
    private TypeEnum _tipus;

    @ColumnInfo(name = "mp")
    private Integer _manapont;

    @ColumnInfo(name = "emp")
    private Integer _emanapont;

    @ColumnInfo(name = "idotartam")
    private String _idotartam;

    @ColumnInfo(name = "hatotav")
    private String _hatotav;

    @ColumnInfo(name = "varazslasideje")
    private String _varazslasideje;

    @ColumnInfo(name = "leiras")
    private String _leiras;

    @ColumnInfo(name = "tablazatok")
    private String _tablatatok;

    @ColumnInfo(name = "specialis")
    private String _specialis;

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    public String getNev() {
        return _nev;
    }

    public void setNev(String nev) {
        _nev = nev;
    }

    public TypeEnum getTipus() {
        return _tipus;
    }

    public void setTipus(TypeEnum tipus) {
        _tipus = tipus;
    }

    public Integer getManapont() {
        return _manapont;
    }

    public void setManapont(Integer manapont) {
        _manapont = manapont;
    }

    public Integer getEmanapont() {
        return _emanapont;
    }

    public void setEmanapont(Integer emanapont) {
        _emanapont = emanapont;
    }

    public String getIdotartam() {
        return _idotartam;
    }

    public void setIdotartam(String idotartam) {
        _idotartam = idotartam;
    }

    public String getHatotav() {
        return _hatotav;
    }

    public void setHatotav(String hatotav) {
        _hatotav = hatotav;
    }

    public String getVarazslasideje() {
        return _varazslasideje;
    }

    public void setVarazslasideje(String varazslasideje) {
        _varazslasideje = varazslasideje;
    }

    public String getLeiras() {
        return _leiras;
    }

    public void setLeiras(String leiras) {
        _leiras = leiras;
    }

    public String getTablatatok() {
        return _tablatatok;
    }

    public void setTablatatok(String tablatatok) {
        _tablatatok = tablatatok;
    }

    public String getSpecialis() {
        return _specialis;
    }

    public void setSpecialis(String specialis) {
        _specialis = specialis;
    }
}
