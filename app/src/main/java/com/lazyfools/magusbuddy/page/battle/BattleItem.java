package com.lazyfools.magusbuddy.page.battle;

import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

public class BattleItem {
    private CharacterEntity character;
    private int currentSegment;

    public CharacterEntity getCharacter() {
        return character;
    }
    public void setCurrentSegment(int t){currentSegment = t;}

    public int getCurrentSegment() {
        return currentSegment;
    }

    BattleItem( CharacterEntity character, int currentSegment){
        this.character = character;
        this.currentSegment = currentSegment;
    }
}
