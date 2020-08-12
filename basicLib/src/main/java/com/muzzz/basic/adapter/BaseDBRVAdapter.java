package com.muzzz.basic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDBRVAdapter<Data, DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRVHolder> {

    private List<Data> mData;
    private int mItemId;
    protected Context mContext;
    protected int mVariableId;
    protected OnItemClickListener<Data> mListener;


    public BaseDBRVAdapter(@LayoutRes int itemId, int variableId) {
        this.mItemId = itemId;
        this.mVariableId = variableId;
        mData = new ArrayList<>();
    }

    public BaseDBRVAdapter(List<Data> data, @LayoutRes int itemId, int variableId) {
        this.mData = data == null ? new ArrayList<Data>() : data;
        this.mItemId = itemId;
        this.mVariableId = variableId;
    }


    @NonNull
    @Override
    public BaseDBRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        this.mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        DB binding = DataBindingUtil.inflate(inflater, mItemId, parent, false);
        return new BaseDBRVHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRVHolder holder, final int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = mData.get(position);
        binding.setVariable(mVariableId, itemData);
        onBindViewHolder(itemData, binding, position);
        //迫使数据立即绑定
        binding.executePendingBindings();
        //设置点击事件
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(itemData, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mListener.onItemLongClick(itemData, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 绑定数据
     */
    protected void onBindViewHolder(Data data, DB binding, int position) {
    }

    /**
     * 设置新数据
     *
     * @param data
     */
    public void setNewData(List<Data> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(Data data) {
        this.mData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<Data> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 设置Item 长按、点击事件
     */
    public void setOnItemListener(OnItemClickListener<Data> listener) {
        this.mListener = listener;
    }
}
