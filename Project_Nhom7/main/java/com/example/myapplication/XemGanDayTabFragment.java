package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class XemGanDayTabFragment extends Fragment {
    ArrayList<Comic> arr;
    GridView gridView;
    ComicGridViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_bookmark, container, false);
        createData();
        setAdapter(view);
        return view;
    }

    private void setAdapter(View view) {
        gridView = (GridView) view.findViewById(R.id.gridViewBookmark);
        adapter = new ComicGridViewAdapter(getActivity(), arr);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ComicDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("comic",  arr.get(position));
                intent.putExtra("package",bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private void createData() {
        arr = new ArrayList<>();

        ArrayList<Chuong> arrChuong = new ArrayList<>();

        MyAppDatabase database = new MyAppDatabase(getActivity().getApplicationContext());

        ArrayList<History> historyList = database.getAllHistory();

        for (History history : historyList){
            arr.add(database.getComic(history.getComicId()));
        }
    }
}