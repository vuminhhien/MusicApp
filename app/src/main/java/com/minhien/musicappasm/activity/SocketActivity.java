package com.minhien.musicappasm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.minhien.musicappasm.R;
import com.minhien.musicappasm.adapter.MessageAdapter;
import com.minhien.musicappasm.model.MessageModel;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class SocketActivity extends AppCompatActivity {
    ListView lvData;
    EditText edtData;
    Button btnData;

    MessageAdapter adapter;
    List<MessageModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        lvData = findViewById(R.id.lvData);
        edtData = findViewById(R.id.edtData);
        btnData = findViewById(R.id.btnData);
        data = new ArrayList<>();
        adapter = new MessageAdapter(data, SocketActivity.this);
        lvData.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:2046/socket.php").build();
        WebSocket socket = client.newWebSocket(request, socketListener);
        client.dispatcher().executorService().shutdown();

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edtData.getText().toString();
                MessageModel mm = new MessageModel(s, true);
                JSONObject object = new JSONObject();
                try {
                    object.put("data", mm.getData());
                    object.put("fromMe", mm.getFromMe());

                    socket.send(object.toString());

                    List<MessageModel> _data = new ArrayList<MessageModel>(data);
                    _data.add(mm);
                    data.clear();
                    data.addAll(_data);
                    adapter.notifyDataSetChanged();
                    lvData.setAdapter(adapter);

                    edtData.setText("");

                    lvData.setSelection(adapter.getCount() - 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    WebSocketListener socketListener = new WebSocketListener() {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            text = text.replace("\\\"", "'");
            text = text.substring(1, text.length() - 1);
            try {
                JSONObject object = new JSONObject(text);
                String d = object.getString("data");
                MessageModel mm = new MessageModel(d, false);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<MessageModel> _data = new ArrayList<MessageModel>(data);
                        _data.add(mm);
                        data.clear();
                        data.addAll(_data);
                        adapter.notifyDataSetChanged();
                        lvData.setAdapter(adapter);
                        lvData.setSelection(adapter.getCount() - 1);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
        }
    };
}