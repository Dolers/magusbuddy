package com.lazyfools.magusbuddy.page.character;

public class CharacterItem {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CharacterItem(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
