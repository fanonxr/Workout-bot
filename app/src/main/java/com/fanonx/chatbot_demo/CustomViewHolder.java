package com.fanonx.chatbot_demo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    /**
     * Custom View holder Class
     * will hold the Message
     * */
    TextView textView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.textMessage);
    }
}
