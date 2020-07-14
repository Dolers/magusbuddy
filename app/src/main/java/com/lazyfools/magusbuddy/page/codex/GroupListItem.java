package com.lazyfools.magusbuddy.page.codex;

import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupListItem {
    public String groupName;
    public List<NameEntity> children;

    public GroupListItem() {
        children = new ArrayList<>();
    }

    public GroupListItem(String groupName, List<NameEntity> children) {
        this.groupName = groupName;
        this.children = children;
    }
}
