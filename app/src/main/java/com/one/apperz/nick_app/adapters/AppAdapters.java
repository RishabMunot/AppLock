package com.one.apperz.nick_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.apperz.nick_app.R;
import com.one.apperz.nick_app.ViewHolder.AppViewHolder;
import com.one.apperz.nick_app.interfaces.AppOnClickListerner;
import com.one.apperz.nick_app.model.AppItem;
import com.one.apperz.nick_app.utils.Utils;

import java.util.List;

public class AppAdapters extends RecyclerView.Adapter<AppViewHolder> {

    private Context mContext;
    private List<AppItem> appItemList;
    private Utils utils;
    public AppAdapters(Context mContext, List<AppItem> appItemList) {
        this.mContext = mContext;
        this.appItemList = appItemList;
        this.utils = new Utils(mContext);
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_apps, parent, false);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder holder, int position) {
        holder.nameApp.setText(appItemList.get(position).getName());
        holder.iconApp.setImageDrawable(appItemList.get(position).getIcon());

        final String pk = appItemList.get(position).getPackageName();

        if (utils.isLock(pk)) {
            holder.lockApp.setImageResource(R.drawable.ic_lock_outline_black_24dp);
        } else {
            holder.lockApp.setImageResource(R.drawable.ic_lock_open_black_24dp);

        }

        holder.setListerner(new AppOnClickListerner() {
            @Override
            public void selectApp(int pos) {
                if (utils.isLock(pk)) {
                    holder.lockApp.setImageResource(R.drawable.ic_lock_open_black_24dp);
                    utils.unlock(pk);
                } else {

                    holder.lockApp.setImageResource(R.drawable.ic_lock_outline_black_24dp);
                    utils.lock(pk);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
