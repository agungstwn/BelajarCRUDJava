package com.project.android.democrudrealm.module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.project.android.democrudrealm.R;
import com.project.android.democrudrealm.module.model.DataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agung on 19/09/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<DataModel> dataModels;

    public BookAdapter(ArrayList<DataModel> dataModels, OnItemClickListener listener) {
        this.dataModels = dataModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.click(dataModels.get(position), listener);
        holder.mId.setText(String.valueOf(dataModels.get(position).getId()));
        holder.mTitle.setText(dataModels.get(position).getTitle());
        holder.mDesc.setText(dataModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvId)
        TextView mId;
        @BindView(R.id.tvTitle)
        TextView mTitle;
        @BindView(R.id.tvDescription)
        TextView mDesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final DataModel dataModel, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(dataModel);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onClick(DataModel item);
    }
}
