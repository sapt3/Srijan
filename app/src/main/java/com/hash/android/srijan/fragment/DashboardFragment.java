package com.hash.android.srijan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hash.android.srijan.DashboardActivity;
import com.hash.android.srijan.EventsActivity;
import com.hash.android.srijan.R;
import com.hash.android.srijan.adapter.DashboardRecyclerAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class DashboardFragment extends android.support.v4.app.Fragment {

//    static ArrayList<Integer> eventArrayListImage;


    public ArrayList<Integer> eventArrayListImage;
    public ArrayList<String> eventArrayListTextContent;
    public ArrayList<String> eventArrayListTextHeading;
    public ArrayList<Integer> eventArrayListIcon;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Srijan '17");
        getActivity().getWindow().setExitTransition(new Explode());

    }

    /**
     * Get the root view for the fragment's layout (the one returned by {@link #onCreateView}),
     * if provided.
     *
     * @return The fragment's root view, or null if it has no layout.
     */
    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        eventArrayListTextHeading = new ArrayList<>();
        eventArrayListImage = new ArrayList<>();
        eventArrayListIcon = new ArrayList<>();
        eventArrayListTextContent = new ArrayList<>();

        updateContent();
        RecyclerView eventsRecyclerView = (RecyclerView) rootView.findViewById(R.id.exploreRecyclerView);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventsRecyclerView.setAdapter(new DashboardRecyclerAdapter(eventArrayListTextHeading, eventArrayListIcon, eventArrayListTextContent, eventArrayListImage));

        eventsRecyclerView.addOnItemTouchListener(new DashboardActivity.RecyclerItemClickListener(getActivity(), eventsRecyclerView, new DashboardActivity.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) throws ExecutionException, InterruptedException {
                Intent i = new Intent(getActivity(), EventsActivity.class);
//                i.putExtra("position", position);
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.frame_layout), "main");

                //create a bundle to send to the activity
                Bundle bundle = getActivity().getIntent().getExtras();
                bundle.putStringArrayList("eventArrayListTextHeading", eventArrayListTextHeading);
                bundle.putIntegerArrayList("eventArrayListImage", eventArrayListImage);
                bundle.putIntegerArrayList("eventArrayListIcon", eventArrayListIcon);
                bundle.putStringArrayList("eventArrayListTextContent", eventArrayListTextContent);
                bundle.putInt("pos", position);
                i.putExtras(bundle);
                startActivity(i);
//                startActivity(i, optionsCompat.toBundle());
//                setExitTransition(new Explode().setDuration(1000));

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return rootView;
//        return  relativeLayout;
    }

    private void updateContent() {
        addEvent(getString(R.string.robticsEventName), R.drawable.roboticsimage, "Welcome to a parallel universe. \nHere bots walk, talk, and their world totally rocks!", R.drawable.robotics);
        addEvent(getString(R.string.codeMe), R.drawable.codingimage, "It's raining bugs and codes every day. \nAll you God gamers, come over and slay!\nThe day is all yours.", R.drawable.codemelogo);
        addEvent(getString(R.string.gaming), R.drawable.gamingimage, "You ask for it, and we shall play the same.\nWinner takes it all, but are you game?", R.drawable.gaminglogo);
        addEvent("ManageMania", R.drawable.manageeimage, "The one who rules the world, what if he gives it a twirl?\nThe businessman scratches his brain, but is everything really in vain?", R.drawable.managelogo);
//        addEvent("Exhibition", R.drawable.exhiimage, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam accumsan feugiat ipsum id imperdiet. In sed turpis odio.", R.drawable.exhilogo);
        addEvent("Mixed bag", R.drawable.miss, "Expect the unexpected, challenge the unknown.\nImmerse yourselves in the mystery, and the path shall be shown.", R.drawable.misslogo);
//        addEvent("Official Merchandise", R.drawable.merch2, "Get merchandise and cool accessories from the biggest tech-fest in town!", R.drawable.merchlogo);
    }

    private void addEvent(String robotech, int codingimage, String s, int robotics) {
        eventArrayListTextHeading.add(robotech);
        eventArrayListImage.add(codingimage);
        eventArrayListTextContent.add(s);
        eventArrayListIcon.add(robotics);
    }


}
