package com.one.apperz.nick_app.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.apperz.nick_app.R;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ImageView iconApp, lockApp;
    public TextView nameApp;

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);
        iconApp = itemView.findViewById(R.id.icon_app);
        lockApp = itemView.findViewById(R.id.lock);
        nameApp = itemView.findViewById(R.id.name_app);

    }
}
