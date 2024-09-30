package com.example.chatbotapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRVAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MessageModel> list;

    public ChatRVAdapter(Context context, ArrayList<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.sender_view, parent, false);
                return new UserView(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.receiver_view, parent, false);
                return new ReceiverView(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel model =  list.get(position);
        switch (model.getViewType()){
            case "user":
                ((UserView)holder).user.setText(model.getMessage());
                break;
            case "bot":
                ((ReceiverView)holder).receiver.setText(model.getMessage());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch(list.get(position).getViewType()){
            case "user" :
                return 0;
            case "bot":
                return 1;
            default:
                return -1;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserView extends RecyclerView.ViewHolder {
        TextView user;
        public UserView(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.sender_text);
        }
    }

    public static class ReceiverView extends RecyclerView.ViewHolder {
        TextView receiver;

        public ReceiverView(@NonNull View itemView) {
            super(itemView);
            receiver = itemView.findViewById(R.id.receiver_text);
        }
    }
}
