package com.lazyfools.magusbuddy.page.codex;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;
import java.util.ArrayList;
import java.util.List;


abstract public class SingleFragment<ViewModel extends AndroidViewModel> extends Fragment {
    protected ViewModel _viewModel;
    private final  Class<ViewModel> _viewModelClass;

    public SingleFragment(Class<ViewModel> viewModelClass){
        _viewModelClass = viewModelClass;
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(_viewModelClass);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext())
                .inflate(R.layout.magic_single_show, container, false);
    }

    public ArrayList<String> populateWithTables() {
        ArrayList<String> descriptionTables = new ArrayList<>();
        if (!descriptionTables.isEmpty()){
            RecyclerView tableListView = getView().findViewById(R.id.table_listview);
            tableListView.setVisibility(View.VISIBLE);
            tableListView.addItemDecoration(new MarginItemDecoration(30,30,0,0));

            DescTableAdapter adapter = new DescTableAdapter(getActivity().getApplicationContext());
            adapter.setItems(descriptionTables);
            tableListView.setAdapter(adapter);
        }
        return descriptionTables;
    }
    public void setAndMakeVisible(View layout, String value, int idToSet){
        if (!value.isEmpty()){
            TextView textView = layout.findViewById(idToSet);
            textView.setText(value);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void setOrHide(View layout, String value, int idToSet, Integer... idsToHide){
        if (value.isEmpty()){
            for (Integer idToHide: idsToHide) {
                layout.findViewById(idToHide).setVisibility(View.GONE);
            }
        }
        else {
            ((TextView)layout.findViewById(idToSet)).setText(value);
        }
    }

    public void setOrHide(View layout, Integer value, int idToSet, Integer... idsToHide){
        if (value == 0){
            for (Integer idToHide: idsToHide) {
                layout.findViewById(idToHide).setVisibility(View.GONE);
            }
        }
        else {
            ((TextView)layout.findViewById(idToSet)).setText(Integer.toString(value));
        }
    }
}
