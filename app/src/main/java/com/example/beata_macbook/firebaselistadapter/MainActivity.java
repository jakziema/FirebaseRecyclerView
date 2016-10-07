package com.example.beata_macbook.firebaselistadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Firebase mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();

    TextView mTextView;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
         mRootRef = new Firebase("https://praca-inzynierska-44519.firebaseio.com");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTextView = (TextView)findViewById(R.id.textView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    protected void onStart() {
        super.onStart();



        Firebase messageRef = mRootRef.child("messages");
        FirebaseRecyclerAdapter<String, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<String, MessageViewHolder>(String.class, android.R.layout.two_line_list_item, MessageViewHolder.class, messageRef) {
            @Override
            protected void populateViewHolder(MessageViewHolder messageViewHolder, String s, int i) {
                messageViewHolder.mText.setText(s);
            }
        };

        mRecyclerView.setAdapter(adapter);

    }

    public static class MessageViewHolder extends  RecyclerView.ViewHolder {
        TextView mText;

        public MessageViewHolder(View v) {
            super(v);
            mText = (TextView) v.findViewById(android.R.id.text1);
        }
    }


}
