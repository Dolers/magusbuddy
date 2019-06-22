package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

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
    private Integer mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "type")
    private QualificationTypeEnum mType;
    
    @ColumnInfo(name = "difficulty")
    private Integer mDifficulty;
    
    @ColumnInfo(name = "strengthReq")
    private Boolean mStrengthReq = false;

    @ColumnInfo(name = "speedReq")
    private Boolean mSpeedReq = false;

    @ColumnInfo(name = "agilityReq")
    private Boolean mAgilityReq = false;

    @ColumnInfo(name = "staminaReq")
    private Boolean mStaminaReq = false;

    @ColumnInfo(name = "healthReq")
    private Boolean mHealthReq = false;

    @ColumnInfo(name = "charismaReq")
    private Boolean mCharismaReq = false;

    @ColumnInfo(name = "IntelligenceReq")
    private Boolean mIntelligenceReq = false;

    @ColumnInfo(name = "willReq")
    private Boolean mWillReq = false;

    @ColumnInfo(name = "AstralReq")
    private Boolean mAstralReq = false;

    @ColumnInfo(name = "PerceptionReq")
    private Boolean mPerceptionReq = false;

    @ColumnInfo(name = "description")
    private String mDescription;

    public QualificationEntity(String mName, QualificationTypeEnum mType, Integer mDifficulty, Boolean mStrengthReq, Boolean mSpeedReq, Boolean mAgilityReq, Boolean mStaminaReq, Boolean mHealthReq, Boolean mCharismaReq, Boolean mIntelligenceReq, Boolean mWillReq, Boolean mAstralReq, Boolean mPerceptionReq, String mDescription) {
        this.mName = mName;
        this.mType = mType;
        this.mDifficulty = mDifficulty;
        this.mStrengthReq = mStrengthReq;
        this.mSpeedReq = mSpeedReq;
        this.mAgilityReq = mAgilityReq;
        this.mStaminaReq = mStaminaReq;
        this.mHealthReq = mHealthReq;
        this.mCharismaReq = mCharismaReq;
        this.mIntelligenceReq = mIntelligenceReq;
        this.mWillReq = mWillReq;
        this.mAstralReq = mAstralReq;
        this.mPerceptionReq = mPerceptionReq;
        this.mDescription = mDescription;
    }

    public Integer getId() { return mId;}
    public void setId(Integer mId) { this.mId = mId; }

    public String getName() { return mName; }
    public void setName(String mName) { this.mName = mName; }

    public QualificationTypeEnum getType() { return mType; }
    public void setType(QualificationTypeEnum mType) { this.mType = mType; }

    public Integer getDifficulty() { return mDifficulty; }
    public void setDifficulty(Integer mDifficulty) { this.mDifficulty = mDifficulty; }

    public Boolean isStrengthReq() { return mStrengthReq; }
    public void setStrengthReq(Boolean mStrengthReq) { this.mStrengthReq = mStrengthReq; }

    public Boolean isSpeedReq() { return mSpeedReq; }
    public void setSpeedReq(Boolean mSpeedReq) { this.mSpeedReq = mSpeedReq; }

    public Boolean isAgilityReq() { return mAgilityReq; }
    public void setAgilityReq(Boolean mAgilityReq) { this.mAgilityReq = mAgilityReq; }

    public Boolean isStaminaReq() { return mStaminaReq; }
    public void setStaminaReq(Boolean mStaminaReq) { this.mStaminaReq = mStaminaReq; }

    public Boolean isHealthReq() { return mHealthReq; }
    public void setHealthReq(Boolean mHealthReq) { this.mHealthReq = mHealthReq; }

    public Boolean isCharismaReq() { return mCharismaReq; }
    public void setCharismaReq(Boolean mCharismaReq) { this.mCharismaReq = mCharismaReq; }

    public Boolean isIntelligenceReq() { return mIntelligenceReq; }
    public void setIntelligenceReq(Boolean mIntelligenceReq) { this.mIntelligenceReq = mIntelligenceReq; }

    public Boolean isWillReq() { return mWillReq; }
    public void setWillReq(Boolean mWillReq) { this.mWillReq = mWillReq; }

    public Boolean isAstralReq() { return mAstralReq; }
    public void setAstralReq(Boolean mAstralReq) { this.mAstralReq = mAstralReq; }

    public Boolean isPerceptionReq() { return mPerceptionReq; }
    public void setmPerceptionReq(Boolean mPerceptionReq) { this.mPerceptionReq = mPerceptionReq; }

    public String getDescription() { return mDescription; }
    public void setDescription(String mDescription) { this.mDescription = mDescription; }

}
