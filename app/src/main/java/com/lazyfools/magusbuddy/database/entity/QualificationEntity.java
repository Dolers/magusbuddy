package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "qualifications")
public class QualificationEntity {
    public enum QualificationTypeEnum{
        HARCI,
        VILAGI,
        SZOCIALIS,
        ALVILAGI,
        TUDOMANYOS,
        MISZTIKUS
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private QualificationTypeEnum _type;

    @ColumnInfo(name = "difficulty")
    private Integer _difficulty;

    @ColumnInfo(name = "strengthReq")
    private Boolean _strengthReq = false;

    @ColumnInfo(name = "speedReq")
    private Boolean _speedReq = false;

    @ColumnInfo(name = "agilityReq")
    private Boolean _agilityReq = false;

    @ColumnInfo(name = "staminaReq")
    private Boolean _staminaReq = false;

    @ColumnInfo(name = "healthReq")
    private Boolean _healthReq = false;

    @ColumnInfo(name = "charismaReq")
    private Boolean _charismaReq = false;

    @ColumnInfo(name = "intelligenceReq")
    private Boolean _intelligenceReq = false;

    @ColumnInfo(name = "willReq")
    private Boolean _willReq = false;

    @ColumnInfo(name = "astralReq")
    private Boolean _astralReq = false;

    @ColumnInfo(name = "perceptionReq")
    private Boolean _perceptionReq = false;

    @ColumnInfo(name = "description")
    private String _description;

    @ColumnInfo(name = "firstLevelDescription")
    private String _firstLevelDesc;

    @ColumnInfo(name = "secondLevelDescription")
    private String _secondLevelDesc;

    @ColumnInfo(name = "thirdLevelDescription")
    private String _thirdLevelDesc;

    @ColumnInfo(name = "fourthLevelDescription")
    private String _fourthLevelDesc;

    @ColumnInfo(name = "fifthLevelDescription")
    private String _fifthLevelDesc;

    @ColumnInfo(name = "specialDescription")
    private String _specialDesc;

    @ColumnInfo(name = "descriptionTables")
    private ArrayList<String> _descriptionTables;


    public QualificationEntity(String _name, QualificationTypeEnum _type, Integer _difficulty, Boolean _strengthReq, Boolean _speedReq, Boolean _agilityReq, Boolean _staminaReq, Boolean _healthReq, Boolean _charismaReq, Boolean _intelligenceReq, Boolean _willReq, Boolean _astralReq, Boolean _perceptionReq, String _description) {
        this._name = _name;
        this._type = _type;
        this._difficulty = _difficulty;
        this._strengthReq = _strengthReq;
        this._speedReq = _speedReq;
        this._agilityReq = _agilityReq;
        this._staminaReq = _staminaReq;
        this._healthReq = _healthReq;
        this._charismaReq = _charismaReq;
        this._intelligenceReq = _intelligenceReq;
        this._willReq = _willReq;
        this._astralReq = _astralReq;
        this._perceptionReq = _perceptionReq;
        this._description = _description;
    }

    public Integer getId() { return _id;}
    public void setId(Integer _id) { this._id = _id; }

    public String getName() { return _name; }
    public void setName(String _name) { this._name = _name; }

    public QualificationTypeEnum getType() { return _type; }
    public void setType(QualificationTypeEnum _type) { this._type = _type; }

    public Integer getDifficulty() { return _difficulty; }
    public void setDifficulty(Integer _difficulty) { this._difficulty = _difficulty; }

    public Boolean isStrengthReq() { return _strengthReq; }
    public void setStrengthReq(Boolean _strengthReq) { this._strengthReq = _strengthReq; }

    public Boolean isSpeedReq() { return _speedReq; }
    public void setSpeedReq(Boolean _speedReq) { this._speedReq = _speedReq; }

    public Boolean isAgilityReq() { return _agilityReq; }
    public void setAgilityReq(Boolean _agilityReq) { this._agilityReq = _agilityReq; }

    public Boolean isStaminaReq() { return _staminaReq; }
    public void setStaminaReq(Boolean _staminaReq) { this._staminaReq = _staminaReq; }

    public Boolean isHealthReq() { return _healthReq; }
    public void setHealthReq(Boolean _healthReq) { this._healthReq = _healthReq; }

    public Boolean isCharismaReq() { return _charismaReq; }
    public void setCharismaReq(Boolean _charismaReq) { this._charismaReq = _charismaReq; }

    public Boolean isIntelligenceReq() { return _intelligenceReq; }
    public void setIntelligenceReq(Boolean _intelligenceReq) { this._intelligenceReq = _intelligenceReq; }

    public Boolean isWillReq() { return _willReq; }
    public void setWillReq(Boolean _willReq) { this._willReq = _willReq; }

    public Boolean isAstralReq() { return _astralReq; }
    public void setAstralReq(Boolean _astralReq) { this._astralReq = _astralReq; }

    public Boolean isPerceptionReq() { return _perceptionReq; }
    public void setmPerceptionReq(Boolean _perceptionReq) { this._perceptionReq = _perceptionReq; }

    public String getDescription() { return _description; }
    public void setDescription(String _description) { this._description = _description; }

    public String getFirstLevelDesc() { return _firstLevelDesc; }
    public void setFirstLevelDesc(String _firstLevelDesc) { this._firstLevelDesc = _firstLevelDesc; }

    public String getSecondLevelDesc() { return _secondLevelDesc; }
    public void setSecondLevelDesc(String _secondLevelDesc) { this._secondLevelDesc = _secondLevelDesc; }

    public String getThirdLevelDesc() { return _thirdLevelDesc; }
    public void setThirdLevelDesc(String _thirdLevelDesc) { this._thirdLevelDesc = _thirdLevelDesc; }

    public String getFourthLevelDesc() { return _fourthLevelDesc; }
    public void setFourthLevelDesc(String _fourthLevelDesc) { this._fourthLevelDesc = _fourthLevelDesc; }

    public String getFifthLevelDesc() { return _fifthLevelDesc; }
    public void setFifthLevelDesc(String _fifthLevelDesc) { this._fifthLevelDesc = _fifthLevelDesc; }

    public String getSpecialDesc() { return _specialDesc; }
    public void setSpecialDesc(String _specialDesc) { this._specialDesc = _specialDesc; }

    public ArrayList<String> getDescriptionTables() { return _descriptionTables; }
    public void setDescriptionTables(ArrayList<String> _descriptionTables) { this._descriptionTables = _descriptionTables; }
}
