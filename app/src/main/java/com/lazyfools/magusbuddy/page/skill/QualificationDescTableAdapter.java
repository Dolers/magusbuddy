package com.lazyfools.magusbuddy.page.skill;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;

import java.util.List;

/**
 Creates tables from formatted texts

 */
public class QualificationDescTableAdapter extends RecyclerView.Adapter<QualificationDescTableAdapter.ViewHolder> {
    private final Context _context;
    private List<String> _tableData;
    private TableRow.LayoutParams _cellParams;
    private TableRow.LayoutParams _rowParams;

    public QualificationDescTableAdapter(Context context) {
        _context = context;
        setupLayoutParams();
    }

    private void parseTable(TableLayout tableLayout, int position) {
        tableLayout.removeAllViews();
        String tableData = _tableData.get(position);

        int rowIt = 0;
        for(String tableRowData : tableData.split("\\n")) {
            TableRow tableRow = new TableRow(_context);
            setRowProperties(tableRow);

            for(String tableCellData : tableRowData.split("\\t")) {
                TextView cellTextView = new TextView(_context);
                cellTextView.setText(tableCellData);
                setCellProperties(cellTextView,rowIt);

                tableRow.addView(cellTextView);
            }
            tableLayout.addView(tableRow);
            rowIt += 1;
        }
    }

    private void setCellProperties(TextView cellTextView, int rowIt) {
        if (rowIt == 0) {
            //ContextCompat.getColor(_context,R.color.colorTableHeaderBackground)
            cellTextView.setBackgroundColor(_context.getResources().getColor(R.color.colorTableHeaderBackground));
            cellTextView.setTextColor(_context.getResources().getColor(R.color.colorTableHeaderText));
        }
        else {
            cellTextView.setBackgroundColor(_context.getResources().getColor(R.color.colorDefaultBackground));
            cellTextView.setTextColor(_context.getResources().getColor(R.color.colorDefaultText));
        }

        cellTextView.setGravity(Gravity.CENTER);

        _cellParams.setMargins(0,2,0,2);
        cellTextView.setLayoutParams(_cellParams);
    }

    private void setupLayoutParams() {
        _cellParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT);
        _cellParams.weight = 1;
        _cellParams.gravity = Gravity.CENTER;
        _cellParams.width = 0;

        _rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        _rowParams.setMargins(0,2,0,2);
    }

    private void setRowProperties(TableRow tableRow) {
        tableRow.setBackgroundColor(Color.BLACK);
        tableRow.setLayoutParams(_rowParams);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_table_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        parseTable(viewHolder._tableLayout, position);
    }

    public void setItems(List<String> tableData){
        _tableData = tableData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (_tableData == null){
            return 0;
        }
        return _tableData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View _view;
        public final TableLayout _tableLayout;

        public ViewHolder(View view) {
            super(view);
            _view = view;
            _tableLayout = view.findViewById(R.id.skill_description_table);
        }
    }
}
