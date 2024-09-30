package com.example.chatbotapplication;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatDelegate;
=======
>>>>>>> origin/master
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.GenerativeModel; // Adjust package if necessary
//import com.google.cloud.gemini.v1beta.model.Content; // Adjust package if necessary


import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
=======
import java.io.IOException;
>>>>>>> origin/master
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    ArrayList<MessageModel> list;
    ChatRVAdapter adapter;
    private final String user = "user";
    private final String bot = "bot";
<<<<<<< HEAD
    private GenerativeModelFutures model;
=======
    private GeminiService geminiService; // Use GeminiService instead of OpenAIService
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

=======
>>>>>>> origin/master
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and EditText
        recyclerView = findViewById(R.id.recycleView);
        editText = findViewById(R.id.edit);
        list = new ArrayList<>();
        adapter = new ChatRVAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

<<<<<<< HEAD
        // Initialize GenerativeModel and Futures
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash", "AIzaSyCE4ojzhrgz4mHQsKlbaa4e2RBuMSiAFEU");
        model = GenerativeModelFutures.from(gm);
=======
        // Scroll to the bottom when new messages are added
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });

        // Initialize GeminiService with API key
        geminiService = new GeminiService(BuildConfig.API_KEY); // Adjust this line as necessary
>>>>>>> origin/master

        // Handle EditText touch event (send message)
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[2].getBounds().width())) {
                        String message = editText.getText().toString().trim();
                        if (message.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        // Add the user's message to the chat
                        list.add(new MessageModel(message, user));
                        adapter.notifyDataSetChanged();
                        editText.setText("");

<<<<<<< HEAD
                        // Send the user's message to the Gemini API
                        sendToGeminiAPI(message);
=======
                        // Send the message to Gemini API
                        new Thread(() -> {
                            try {
                                String response = geminiService.getResponseFromGemini(message); // Adjust this line

                                runOnUiThread(() -> {
                                    list.add(new MessageModel(response, bot));
                                    adapter.notifyDataSetChanged();
                                });
                            } catch (IOException e) {
                                runOnUiThread(() -> {
                                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }
                        }).start();
>>>>>>> origin/master

                        return true;
                    }
                }
                return false;
            }
        });
    }
<<<<<<< HEAD

    private void sendToGeminiAPI(String message) {
        Content content = new Content.Builder().addText(message).build();
        Executor executor = Executors.newSingleThreadExecutor();
        ListenableFuture<GenerateContentResponse> futureResponse = model.generateContent(content);

        Futures.addCallback(futureResponse, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String response = result.getText();
                // Update UI with bot's response
                runOnUiThread(() -> {
                    list.add(new MessageModel(response.trim(), bot));
                    adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(Throwable t) {
                // Display error message
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }, executor);
    }
=======
>>>>>>> origin/master
}
