package com.example.baksteen_13.dinopackopening;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import static com.example.baksteen_13.dinopackopening.R.*;

public class FirstFragment extends Fragment{


    private TextView mTextMessage;
    private ArrayList<String> al;
    private ArrayAdapter arrayAdapter;
    private int i;

    private FirebaseAuth mAuth;
    private String currentUId;
    private DatabaseReference usersDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myView = inflater.inflate(layout.activity_main, container, false);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        currentUId = mAuth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");


        super.onCreate(savedInstanceState);

        al = new ArrayList<>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");

        arrayAdapter = new ArrayAdapter<>(getActivity(), layout.item, id.helloText, al );


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) myView.findViewById(id.frame);

        flingContainer.setAdapter(arrayAdapter);


        ImageButton dislike = (ImageButton) myView.findViewById(id.btndislike);
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) getView().findViewById(id.frame);
                //Snackbar.make(v, "DISLIKE", Snackbar.LENGTH_LONG).show();
                flingContainer.getTopCardListener().selectLeft();
            }
        });

        ImageButton like = (ImageButton) myView.findViewById(id.btnlike);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) getView().findViewById(id.frame);
                //Snackbar.make(view, "LIKE", Snackbar.LENGTH_LONG).show();
                flingContainer.getTopCardListener().selectRight();

            }
        });
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Snackbar.make(myView, "DISLIKE", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Snackbar.make(myView, "LIKE", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                 // View view = flingContainer.getSelectedView();
                 // view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                 // view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }


        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentmanager = getFragmentManager();
                fragmentmanager.beginTransaction().replace(R.id.content_frame, new InfoFragment()).commit();
            }
        });
        return myView;
    }/*end oncreateview()*/

}
