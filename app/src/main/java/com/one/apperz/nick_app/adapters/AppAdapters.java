package com.one.apperz.nick_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.apperz.nick_app.R;
import com.one.apperz.nick_app.ViewHolder.AppViewHolder;
import com.one.apperz.nick_app.model.AppItem;

import java.util.List;

public class AppAdapters extends RecyclerView.Adapter<AppViewHolder> {

    private Context mContext;
    private List<AppItem> appItemList;

    public AppAdapters(Context mContext, List<AppItem> appItemList) {
        this.mContext = mContext;
        this.appItemList = appItemList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_apps, parent, false);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.nameApp.setText(appItemList.get(position).getName());
        holder.iconApp.setImageDrawable(appItemList.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
