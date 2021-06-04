package com.nusademy.nusademy.ui.chatbot4java;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nusademy.nusademy.R;

import java.util.List;

public class AdapterChatbot extends RecyclerView.Adapter<AdapterChatbot.MyViewHolder> {

  private List<MessagesChatbot> listMsgChatbot;
  private Activity activityChatbot;

  public AdapterChatbot(List<MessagesChatbot> messagesChatbotList, Activity activity) {
    this.listMsgChatbot = messagesChatbotList;
    this.activityChatbot = activity;
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(activityChatbot).inflate(R.layout.chatbot_messages, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String message = listMsgChatbot.get(position).messagesGet();
    boolean isReceived = listMsgChatbot.get(position).receivingGet();
     if(isReceived){
       holder.receivingMsg.setVisibility(View.VISIBLE);
       holder.sendingMsg.setVisibility(View.GONE);
       holder.receivingMsg.setText(message);
     }else {
       holder.sendingMsg.setVisibility(View.VISIBLE);
       holder.receivingMsg.setVisibility(View.GONE);
       holder.sendingMsg.setText(message);
     }
  }

  @Override public int getItemCount() {
    return listMsgChatbot.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView sendingMsg;
    TextView receivingMsg;

    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      sendingMsg = itemView.findViewById(R.id.sending_msg);
      receivingMsg = itemView.findViewById(R.id.receiving_msg);
    }
  }

}
