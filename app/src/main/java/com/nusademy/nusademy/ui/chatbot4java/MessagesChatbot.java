package com.nusademy.nusademy.ui.chatbot4java;

public class MessagesChatbot {

  private String chatbotMessages;
  private boolean receivingMessages;

  public MessagesChatbot(String chatbotMessages, boolean receivingMessages) {
    this.chatbotMessages = chatbotMessages;
    this.receivingMessages = receivingMessages;
  }

  public String messagesGet() {
    return chatbotMessages;
  }

  public void setMessage(String message) {
    this.chatbotMessages = message;
  }

  public boolean receivingGet() {
    return receivingMessages;
  }
}
