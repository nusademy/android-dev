package com.nusademy.nusademy.ui.chatbot4java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;
import com.nusademy.nusademy.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MainChatbot4Activity extends AppCompatActivity implements ReplyingBot {

    RecyclerView viewingChatbot;
    AdapterChatbot adapterChatbot;
    List<MessagesChatbot> messagesChatbotList = new ArrayList<>();
    EditText messagesEditing;
    ImageButton sendingButton;

    private SessionsClient clientSession;
    private SessionName namebotSession;
    private String randomUuid = UUID.randomUUID().toString();
    private String tagChatbot = "mainchatbotactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatbot4);
        viewingChatbot = findViewById(R.id.viewingChatbot);
        messagesEditing = findViewById(R.id.messagingEdit);
        sendingButton = findViewById(R.id.sendingButton);

        adapterChatbot = new AdapterChatbot(messagesChatbotList, this);
        viewingChatbot.setAdapter(adapterChatbot);

        sendingButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String clickMessages = messagesEditing.getText().toString();
                if (!clickMessages.isEmpty()) {
                    messagesChatbotList.add(new MessagesChatbot(clickMessages, false));
                    messagesEditing.setText("");
                    sendingMsgChatbot(clickMessages);
                    Objects.requireNonNull(viewingChatbot.getAdapter()).notifyDataSetChanged();
                    Objects.requireNonNull(viewingChatbot.getLayoutManager())
                            .scrollToPosition(messagesChatbotList.size() - 1);
                } else {
                    Toast.makeText(MainChatbot4Activity.this, "Please enter text!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        settingChatbot();
    }

    private void settingChatbot() {
        try {
            InputStream stream = this.getResources().openRawResource(R.raw.testagentcredentials);
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
                    FixedCredentialsProvider.create(credentials)).build();
            clientSession = SessionsClient.create(sessionsSettings);
            namebotSession = SessionName.of(projectId, randomUuid);

            Log.d(tagChatbot, "projectId : " + projectId);
        } catch (Exception e) {
            Log.d(tagChatbot, "setUpBot: " + e.getMessage());
        }
    }

    private void sendingMsgChatbot(String message) {
        QueryInput queriesInput = QueryInput.newBuilder()
                .setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
        new ChatbotSendsMessages(this, namebotSession, clientSession, queriesInput).execute();
    }

    @Override
    public void chatbotCallback(DetectIntentResponse responingReturn) {
        if(responingReturn!=null) {
            String botReply = responingReturn.getQueryResult().getFulfillmentText();
            if(!botReply.isEmpty()){
                messagesChatbotList.add(new MessagesChatbot(botReply, true));
                adapterChatbot.notifyDataSetChanged();
                Objects.requireNonNull(viewingChatbot.getLayoutManager()).scrollToPosition(messagesChatbotList.size() - 1);
            }else {
                Toast.makeText(this, "sesuatu ada yang salah", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "gagal untuk terkoneksi", Toast.LENGTH_SHORT).show();
        }
    }
}