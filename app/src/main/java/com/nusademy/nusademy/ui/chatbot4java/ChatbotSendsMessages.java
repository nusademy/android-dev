package com.nusademy.nusademy.ui.chatbot4java;

import android.os.AsyncTask;
import android.util.Log;

import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;

public class ChatbotSendsMessages extends AsyncTask<Void, Void, DetectIntentResponse> {


  private SessionName chatbotSession;
  private SessionsClient clientSession;
  private QueryInput includeQuerys;
  private String TAG = "async";
  private ReplyingBot chatbotReply;

  public ChatbotSendsMessages(ReplyingBot chatbotReply,
                              SessionName chatbotSession,
                              SessionsClient clientSession,
                              QueryInput includeQuerys) {
    this.chatbotReply = chatbotReply;
    this.chatbotSession = chatbotSession;
    this.clientSession = clientSession;
    this.includeQuerys = includeQuerys;
  }

  @Override
  protected DetectIntentResponse doInBackground(Void... voids) {
    try {
      DetectIntentRequest detectIntentRequest =
          DetectIntentRequest.newBuilder()
              .setSession(chatbotSession.toString())
              .setQueryInput(includeQuerys)
              .build();
      return clientSession.detectIntent(detectIntentRequest);
    } catch (Exception e) {
      Log.d(TAG, "doInBackground: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(DetectIntentResponse response) {
    chatbotReply.chatbotCallback(response);
  }
}