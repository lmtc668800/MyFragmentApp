package com.example.summer.myfragmentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by summer on 16/5/18.
 */
public class TitlesFragment extends ListFragment{
    public final static String EXTRA_POSITION =
            "com.example.summer.myfragmentapp.POSITION";
    private OnTitleSelectedListener listener;
    private boolean isDualPane;
    private int savedPositon;



    public TitlesFragment() {}

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnTitleSelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    " must implement OnTitleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                News.Titles
        ));

        View detailFrame = getActivity().findViewById(R.id.detailFrame);
        isDualPane = detailFrame !=null && detailFrame.getVisibility()== View.VISIBLE;

        if (isDualPane){
            if (savedInstanceState != null){
                savedPositon = savedInstanceState.getInt("saved_position");
            }else{
                savedPositon =0;
            }
            listener.onTitleSelected(savedPositon);

        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saved_position", savedPositon);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        savedPositon = position;
        if (isDualPane) {
            listener.onTitleSelected(position);

        }else{
            Intent intent = new Intent(getActivity(),SubActivity.class);
            intent.putExtra(EXTRA_POSITION,position);
            startActivity(intent);
        }
    }
}
