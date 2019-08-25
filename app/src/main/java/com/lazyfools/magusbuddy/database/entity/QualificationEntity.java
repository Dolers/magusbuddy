package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "qualifications")
public class QualificationEntity {
    public enum TypeEnum {
        HARCI("Harci"),
        VILAGI("Világi"),
        SZOCIALIS("Szociális"),
        ALVILAGI("Alvilági"),
        TUDOMANYOS("Tudományos"),
        MISZTIKUS("Misztikus");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        //public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() {
            return this.name;
        }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

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


    public QualificationEntity(String name, TypeEnum type, Integer difficulty, Boolean strengthReq, Boolean speedReq, Boolean agilityReq, Boolean staminaReq, Boolean healthReq, Boolean charismaReq, Boolean intelligenceReq, Boolean willReq, Boolean astralReq, Boolean perceptionReq, String description) {
        this._name = name;
        this._type = type;
        this._difficulty = difficulty;
        this._strengthReq = strengthReq;
        this._speedReq = speedReq;
        this._agilityReq = agilityReq;
        this._staminaReq = staminaReq;
        this._healthReq = healthReq;
        this._charismaReq = charismaReq;
        this._intelligenceReq = intelligenceReq;
        this._willReq = willReq;
        this._astralReq = astralReq;
        this._perceptionReq = perceptionReq;
        this._description = description;
    }

    public Integer getId() { return _id;}
    public void setId(Integer id) { this._id = id; }

    public String getName() { return _name; }
    public void setName(String name) { this._name = name; }

    public TypeEnum getType() { return _type; }
    public void setType(TypeEnum type) { this._type = type; }

    public Integer getDifficulty() { return _difficulty; }
    public void setDifficulty(Integer difficulty) { this._difficulty = difficulty; }

    public Boolean isStrengthReq() { return _strengthReq; }
    public void setStrengthReq(Boolean strengthReq) { this._strengthReq = strengthReq; }

    public Boolean isSpeedReq() { return _speedReq; }
    public void setSpeedReq(Boolean speedReq) { this._speedReq = speedReq; }

    public Boolean isAgilityReq() { return _agilityReq; }
    public void setAgilityReq(Boolean agilityReq) { this._agilityReq = agilityReq; }

    public Boolean isStaminaReq() { return _staminaReq; }
    public void setStaminaReq(Boolean staminaReq) { this._staminaReq = staminaReq; }

    public Boolean isHealthReq() { return _healthReq; }
    public void setHealthReq(Boolean healthReq) { this._healthReq = healthReq; }

    public Boolean isCharismaReq() { return _charismaReq; }
    public void setCharismaReq(Boolean charismaReq) { this._charismaReq = charismaReq; }

    public Boolean isIntelligenceReq() { return _intelligenceReq; }
    public void setIntelligenceReq(Boolean intelligenceReq) { this._intelligenceReq = intelligenceReq; }

    public Boolean isWillReq() { return _willReq; }
    public void setWillReq(Boolean willReq) { this._willReq = willReq; }

    public Boolean isAstralReq() { return _astralReq; }
    public void setAstralReq(Boolean astralReq) { this._astralReq = astralReq; }

    public Boolean isPerceptionReq() { return _perceptionReq; }
    public void setmPerceptionReq(Boolean perceptionReq) { this._perceptionReq = perceptionReq; }

    public String getDescription() { return _description; }
    public void setDescription(String description) { this._description = description; }

    public String getFirstLevelDesc() { return _firstLevelDesc; }
    public void setFirstLevelDesc(String firstLevelDesc) { this._firstLevelDesc = firstLevelDesc; }

    public String getSecondLevelDesc() { return _secondLevelDesc; }
    public void setSecondLevelDesc(String secondLevelDesc) { this._secondLevelDesc = secondLevelDesc; }

    public String getThirdLevelDesc() { return _thirdLevelDesc; }
    public void setThirdLevelDesc(String thirdLevelDesc) { this._thirdLevelDesc = thirdLevelDesc; }

    public String getFourthLevelDesc() { return _fourthLevelDesc; }
    public void setFourthLevelDesc(String fourthLevelDesc) { this._fourthLevelDesc = fourthLevelDesc; }

    public String getFifthLevelDesc() { return _fifthLevelDesc; }
    public void setFifthLevelDesc(String fifthLevelDesc) { this._fifthLevelDesc = fifthLevelDesc; }

    public String getSpecialDesc() { return _specialDesc; }
    public void setSpecialDesc(String specialDesc) { this._specialDesc = specialDesc; }

    public ArrayList<String> getDescriptionTables() { return _descriptionTables; }
    public void setDescriptionTables(ArrayList<String> descriptionTables) { this._descriptionTables = descriptionTables; }
}
