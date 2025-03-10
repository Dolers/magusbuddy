package com.lazyfools.magusbuddy.page.codex;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.page.common.NestedListView;
import com.lazyfools.magusbuddy.page.common.onClickListener;
import com.lazyfools.magusbuddy.utility.BasicCallback;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.lazyfools.magusbuddy.utility.Utility.getSmallCapsString;

public class GroupListAdapter<Listener extends onClickListener<NameEntity>> extends RecyclerView.Adapter<GroupListAdapter.ViewHolder>
{
    private SparseArray<GroupListItem> _permanentGroups;
    private SparseArray<GroupListItem> _filteredGroups;
    public Activity _activity;
    private Listener _listener;
    private Integer _presetSize = 0;
    private List<BasicCallback> _allItemLoadedCallback;

    public GroupListAdapter(Activity act, Listener listener) {
        _activity = act;
        _listener = listener;
        _permanentGroups = new SparseArray<>();
        _filteredGroups = new SparseArray<>();
        _allItemLoadedCallback = new ArrayList<>();
    }

    public GroupListAdapter(Activity act, Listener listener,  SparseArray<GroupListItem> groups) {
        _activity = act;
        _listener = listener;
        _permanentGroups = groups;
        _filteredGroups = groups;
        _allItemLoadedCallback = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grouplistview_groupitem, parent, false);
        return new GroupListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupListAdapter.ViewHolder holder, int position) {
        holder.item = _filteredGroups.get(_filteredGroups.keyAt(position));
        holder.groupNameTextView.setText(getSmallCapsString(holder.item.groupName));
        holder.detailListView.setAdapter(
            new ArrayAdapter<NameEntity>(_activity,
                                         R.layout.grouplistview_groupdetail,
                                         holder.item.children)
        );

        holder.detailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int listPosition, long id) {
                _listener.onClick(holder.item.children.get(listPosition));
            }
        });
    }

    public void addAllItemLoadedCallback(BasicCallback cb){
        if (_presetSize == 0)
            throw new ExceptionInInitializerError("Cannot use addAllItemLoadedCallback, if size not set");

        _allItemLoadedCallback.add(cb);
    }

    @Override
    public int getItemCount() {
        if (_filteredGroups == null){
            return 0;
        }
        return _filteredGroups.size();
    }

    public void setItem(Integer key, GroupListItem group) {
        if (_presetSize == 0)
            throw new ExceptionInInitializerError("Cannot use setItem, if size not set");

        _permanentGroups.put(key,group);
        _filteredGroups.put(key,group);

        if (_presetSize == _permanentGroups.size()){
            notifyDataSetChanged();
            for (BasicCallback cb : _allItemLoadedCallback) {
                cb.callback();
            }
        }
    }

    public void filter(String text) {
        _filteredGroups.clear();
        if(text.isEmpty()){
            _filteredGroups = _permanentGroups.clone();
            notifyDataSetChanged();
            return;
        }

        Pattern regex = Pattern.compile(Pattern.quote(text), Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < _permanentGroups.size(); i++) {
            int key = _permanentGroups.keyAt(i);
            GroupListItem groupItem = _permanentGroups.get(key);
            GroupListItem filteredGroupItem = new GroupListItem();
            
            for(NameEntity nameEntity : groupItem.children) {
                if (regex.matcher(nameEntity._name).find())
                    filteredGroupItem.children.add(nameEntity);
            }
            if (filteredGroupItem.children.size() > 0){
                filteredGroupItem.groupName = groupItem.groupName;
                _filteredGroups.put(key,filteredGroupItem);
            }
        }
        notifyDataSetChanged();
    }

    public void setItems(SparseArray<GroupListItem> array) {
        _permanentGroups = array;
        _filteredGroups = _permanentGroups.clone();
        notifyDataSetChanged();
    }

    public void setSize(int size) {
        _presetSize = size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView groupNameTextView;
        public final NestedListView detailListView;
        public GroupListItem item;

        public ViewHolder(View view) {
            super(view);
            groupNameTextView = view.findViewById(R.id.group_text);
            detailListView = (NestedListView) view.findViewById(R.id.detail_list);
        }
    }
}
