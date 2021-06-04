package com.nusademy.nusademy.ui.chatbot4java;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;

public interface ReplyingBot {

  void chatbotCallback(DetectIntentResponse responingReturn);
}
