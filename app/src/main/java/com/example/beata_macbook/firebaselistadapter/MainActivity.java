package com.example.beata_macbook.firebaselistadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
         mRootRef = new Firebase("https://praca-inzynierska-44519.firebaseio.com");


        mListView = (ListView)findViewById(R.id.listView);
        mTextView = (TextView)findViewById(R.id.textView);


    }

    protected void onStart() {
        super.onStart();



        Firebase messageRef = mRootRef.child("messages");
        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this,String.class, android.R.layout.simple_list_item_1, messageRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textView = (TextView)view.findViewById(android.R.id.text1);
                textView.setText(s);
            }
        };
                mListView.setAdapter(adapter);
    }


}
