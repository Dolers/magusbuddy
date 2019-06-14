package com.lazyfools.magusbuddy.battle;

import com.lazyfools.magusbuddy.character.CharacterItem;
import com.lazyfools.magusbuddy.database.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

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
