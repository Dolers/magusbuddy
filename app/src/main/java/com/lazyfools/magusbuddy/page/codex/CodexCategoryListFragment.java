package com.lazyfools.magusbuddy.page.codex;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.page.common.onClickListener;
import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;

import java.util.List;

/**
 * A fragment representing a list of picture items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onClickListener}
 * interface.
 */
public class CodexCategoryListFragment extends Fragment {

    private CodexCategoryOnClickListener _listener;
    private DatabaseViewModel _viewModel;
    private CodexCategoryListAdapter _adapter;
    private Parcelable _state;
    private RecyclerView _recyclerView;

    @Override
    public void onPause() {
        _state = _recyclerView.getLayoutManager().onSaveInstanceState();
        super.onPause();
    }

    @Override
    public void onStop() {
        _state = _recyclerView.getLayoutManager().onSaveInstanceState();
        super.onStop();
    }
    public CodexCategoryListFragment() { }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        _viewModel.getAllCodex().observe(this, new Observer<List<CodexEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CodexEntity> entities) {
                _adapter.setItems(entities);
                _recyclerView.getLayoutManager().onRestoreInstanceState(_state);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        _recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.category_list, container, false);
        _recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        _listener = new CodexCategoryOnClickListener(){
            @Override
            public void onClick(CodexEntity item) {
                Navigation.findNavController(_recyclerView).navigate(getNavigateAction(item));
            }
        };

        _adapter = new CodexCategoryListAdapter(_listener,container.getContext());
        _recyclerView.setAdapter(_adapter);

        return _recyclerView;
    }

    private int getNavigateAction(CodexEntity item)
    {
        switch (item.getType()){
            case QUALIFICATION: return R.id.action_navigation_codex_to_qualificationCategoryListFragment;
            case BATTLESITUATION: return R.id.action_navigation_codex_to_battlesituationCategoryListFragment;
            case BARDMAGIC: return R.id.action_navigation_codex_to_bardMagicCategoryListFragment;
            case WITCHMAGIC: return R.id.action_navigation_codex_to_witchMagicCategoryListFragment;
            case WARLOCKMAGIC: return R.id.action_navigation_codex_to_warlockMagicCategoryListFragment;
            case HIGHMAGIC: return R.id.action_navigation_codex_to_highMagicCategoryListFragment;
            case PSZIMAGIC: return R.id.action_navigation_codex_to_psziMagicCategoryListFragment;
            case SACRALMAGIC: return R.id.action_navigation_codex_to_sacralMagicCategoryListFragment;
            case FIREMAGIC: return R.id.action_navigation_codex_to_fireMagicCategoryListFragment;
        }
        return R.id.action_navigation_codex_to_qualificationCategoryListFragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _listener = null;
    }

    public interface CodexCategoryOnClickListener extends onClickListener<CodexEntity> {}
}
