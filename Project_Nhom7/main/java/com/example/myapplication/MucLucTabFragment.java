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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MucLucTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mucluc_fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        final ArrayList<Chuong> arr = (ArrayList<Chuong>) getArguments().getSerializable("data");
        final int comicId = getArguments().getInt("comic_id");
        ListView listView =(ListView) view.findViewById(R.id.listview);
        MucLucListViewAdapter adapter = new MucLucListViewAdapter(this.getActivity(), R.layout.mucluc_listview_item, arr);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("data", arr.get(position).getNoiDungHinhAnh());
                bundle.putInt("comic_id", comicId);
                bundle.putInt("chap_id", arr.get(position).getId());
                Intent intent = new Intent(getActivity().getApplicationContext(), ReadActivity.class);
                intent.putExtra("package", bundle);

                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });

        return view;
    }
}



