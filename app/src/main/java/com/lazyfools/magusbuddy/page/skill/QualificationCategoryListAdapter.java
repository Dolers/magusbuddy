package com.lazyfools.magusbuddy.page.skill;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualificationCategoryListAdapter extends RecyclerView.Adapter<QualificationCategoryListAdapter.ViewHolder> {
    private final Context _context;
    private List<QualificationType> _items;
    private QualificationCategoryListFragment.onClickListener _listener;
    private final Map<QualificationEntity.TypeEnum,Integer> qualificationDrawableIds = new HashMap<QualificationEntity.TypeEnum, Integer>(){
        {
            put(QualificationEntity.TypeEnum.HARCI,R.drawable.kepzettseg_harci);
            put(QualificationEntity.TypeEnum.VILAGI, R.drawable.kepzettseg_vilagi);
            put(QualificationEntity.TypeEnum.SZOCIALIS, R.drawable.kepzettseg_szocialis);
            put(QualificationEntity.TypeEnum.ALVILAGI, R.drawable.kepzettseg_alvilagi);
            put(QualificationEntity.TypeEnum.TUDOMANYOS, R.drawable.kepzettseg_tudomanyos);
            put(QualificationEntity.TypeEnum.MISZTIKUS, R.drawable.kepzettseg_misztikus);
        }
    };

    public QualificationCategoryListAdapter(QualificationCategoryListFragment.onClickListener listener, Context context) {
        _listener = listener;
        _context = context;
    }

    @Override
    public QualificationCategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_category_item, parent, false);
        return new QualificationCategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QualificationCategoryListAdapter.ViewHolder holder, int position) {
        holder._item = _items.get(position);
        holder._contentView.setText(_items.get(position).getType().toString());
        holder._imageView.setImageDrawable(getDrawable(holder._item.type));

        holder._view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != _listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    _listener.onClick(holder._item);
                }
            }
        });
    }

    private Drawable getDrawable(QualificationEntity.TypeEnum type) {
        return _context.getResources().getDrawable(qualificationDrawableIds.get(type));
    }

    @Override
    public int getItemCount() {
        if (_items == null){
            return 0;
        }
        return _items.size();
    }

    public void setItems(List<QualificationType> items) {
        this._items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View _view;
        public final TextView _contentView;
        public final ImageView _imageView;
        public QualificationType _item;

        public ViewHolder(View view) {
            super(view);
            _view = view;
            _contentView = view.findViewById(R.id.text);
            _imageView = view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + _contentView.getText() + "'";
        }
    }
}
