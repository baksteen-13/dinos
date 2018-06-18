package com.example.baksteen_13.dinopackopening;

import android.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import static com.example.baksteen_13.dinopackopening.R.*;

public class FirstFragment extends Fragment{


    private TextView mTextMessage;
    private ArrayList<String> al;
    private ArrayAdapter arrayAdapter;
    private int i;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(layout.activity_main, container, false);





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


        /*@OnClick(id.right)
        public void right() {
            /**
             * Trigger the right event manually.

            flingContainer.getTopCardListener().selectRight();
        }

        @OnClick(id.left)
        public void left() {
            flingContainer.getTopCardListener().selectLeft();
        }*/



        Button dislike = (Button) myView.findViewById(id.btndislike);
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) getView().findViewById(id.frame);
                Snackbar.make(v, "DISLIKE", Snackbar.LENGTH_LONG);
                flingContainer.getTopCardListener().selectLeft();
            }
        });

        Button like = (Button) myView.findViewById(id.btnlike);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) getView().findViewById(id.frame);
                Snackbar.make(view, "LIKE", Snackbar.LENGTH_LONG);
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
                Toast.makeText(getActivity(), "Je vind deze dino niet leuk!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "Je vind deze dino leuk!", Toast.LENGTH_SHORT).show();
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
                //  View view = flingContainer.getSelectedView();
                //  view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                //  view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }


        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
            }
        });
        return myView;
    }/*end oncreateview()*/

}
