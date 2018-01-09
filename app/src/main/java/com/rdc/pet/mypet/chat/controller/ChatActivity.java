package com.rdc.pet.mypet.chat.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rdc.pet.mypet.R;
import com.rdc.pet.mypet.chat.adapter.TextAdapter;
import com.rdc.pet.mypet.chat.model.HttpData;
import com.rdc.pet.mypet.chat.model.HttpGetData;
import com.rdc.pet.mypet.chat.model.ListData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static com.rdc.pet.mypet.config.ApiConfig.API_ADDRES;
import static com.rdc.pet.mypet.config.ApiConfig.API_KEY;

public class ChatActivity extends Activity implements View.OnClickListener, HttpGetData{

    private ListView listView;
    private Button sendButton;
    private EditText editText;

    private String content;
    private double curTime;
    private double preTime = 0;

    private List<ListData> listDatas;
    private TextAdapter adapter;

    private HttpData httpData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {

        listView = (ListView) findViewById(R.id.chat_listView);
        sendButton = (Button) findViewById(R.id.chat_sendButton);
        editText = (EditText) findViewById(R.id.chat_editText);

        listDatas = new ArrayList<ListData>();
        adapter = new TextAdapter(this, listDatas);

        listView.setAdapter(adapter);

        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        content = editText.getText().toString();
        editText.setText("");
        String dropBlank = content.replace(" ", "");
        String dropLine = dropBlank.replace("\n", "");
        ListData listData = null;
        listData = new ListData(content, listData.send, getTime());
        listDatas.add(listData);
        /*if(listDatas.size() > 30) {
            for(int i = 0; i < listDatas.size(); i ++) {
                listDatas.remove(i);
            }
        }*/
        adapter.notifyDataSetChanged();
        httpData = (HttpData) new HttpData(API_ADDRES + "?key=" + API_KEY + "&info=" + dropLine, this).execute();
    }

    @Override
    public void getDataUrl(String data) {
        if(data == "") {
            ListData listData = null;
            listData = new ListData("error", listData.receiver, getTime());
            listDatas.add(listData);
            adapter.notifyDataSetChanged();
        } else {
            parseText(data);
        }
    }

    private void parseText(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            ListData listData = null;
            listData = new ListData(jsonObject.getString("text"), listData.receiver, getTime());
            listDatas.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getTime() {
        curTime -= System.currentTimeMillis();
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = simpleDataFormat.format(curDate);
        if(curTime - preTime >= 5 * 60 * 1000) {
            preTime = curTime;
            return str;
        } else {
            return "";
        }

    }
}
