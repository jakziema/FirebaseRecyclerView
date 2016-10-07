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

        //ADAPTER
         final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMessages);
        mListView.setAdapter(adapter);

        Firebase messageRef = mRootRef.child("messages");
        messageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = (String) dataSnapshot.getValue();
                Log.v("E_CHILD_ADDED", message);

                mMessages.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String message = (String) dataSnapshot.getValue();
                Log.v("E_CHILD_CHANGED", message);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String message = (String) dataSnapshot.getValue();
                Log.v("E_CHILD_REMOVED", message);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                String message = (String) dataSnapshot.getValue();
                Log.v("E_CHILD_MOVED", message);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
