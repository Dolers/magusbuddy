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
    private final Context mContext;
    private List<String> mTableData;
    private TableRow.LayoutParams mCellParams;
    private TableRow.LayoutParams mRowParams;

    public QualificationDescTableAdapter(Context context) {
        mContext = context;
        setupLayoutParams();
    }

    private void parseTable(TableLayout tableLayout, int position) {
        tableLayout.removeAllViews();
        String tableData = mTableData.get(position);

        int rowIt = 0;
        for(String tableRowData : tableData.split("\\n")) {
            TableRow tableRow = new TableRow(mContext);
            setRowProperties(tableRow);

            for(String tableCellData : tableRowData.split("\\t")) {
                TextView cellTextView = new TextView(mContext);
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
            //ContextCompat.getColor(mContext,R.color.colorTableHeaderBackground)
            cellTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colorTableHeaderBackground));
            cellTextView.setTextColor(mContext.getResources().getColor(R.color.colorTableHeaderText));
        }
        else {
            cellTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colorDefaultBackground));
            cellTextView.setTextColor(mContext.getResources().getColor(R.color.colorDefaultText));
        }

        cellTextView.setGravity(Gravity.CENTER);

        mCellParams.setMargins(0,2,0,2);
        cellTextView.setLayoutParams(mCellParams);
    }

    private void setupLayoutParams() {
        mCellParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT);
        mCellParams.weight = 1;
        mCellParams.gravity = Gravity.CENTER;
        mCellParams.width = 0;

        mRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        mRowParams.setMargins(0,2,0,2);
    }

    private void setRowProperties(TableRow tableRow) {
        tableRow.setBackgroundColor(Color.BLACK);
        tableRow.setLayoutParams(mRowParams);
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
        parseTable(viewHolder.mTableLayout, position);
    }

    public void setItems(List<String> tableData){
        mTableData = tableData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTableData == null){
            return 0;
        }
        return mTableData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TableLayout mTableLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTableLayout = view.findViewById(R.id.skill_description_table);
        }
    }
}
