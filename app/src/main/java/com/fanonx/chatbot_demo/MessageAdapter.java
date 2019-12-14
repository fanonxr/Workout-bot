package com.fanonx.chatbot_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {

    /**
     * CustomViewHolder class
     * will hold the text message
     * */
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

    /**
     * Fields
     * list to store the response messages.
     * */
    List<ResponseMessage> responseMessageList;

    /**
     * Constructor. */
    public MessageAdapter(List<ResponseMessage> responseMessageList) {
        this.responseMessageList = responseMessageList;
    }

    /**
     * method to create whether is should be a user message or a chat bot message.
     * @param position the index in the list to get the message
     * @return a view object of chat bot or user */
    @Override
    public int getItemViewType(int position) {
        // check to see if it should be a chat bot response or a user response
        if(responseMessageList.get(position).isUser()) {
            return R.layout.user_bubble;
        }
        return R.layout.bot_bubble;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    /**
     * bind the message to the view by getting the text message from the list.*/
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textView.setText(responseMessageList.get(position).getTextMessage());
    }

    /**
     * the total amount of messages in the list*/
    @Override
    public int getItemCount() {
        return responseMessageList.size();
    }
}
