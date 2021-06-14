package com.example.messedup.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messedup.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeFragment extends Fragment {

    RecyclerView noticeRecycler;
    NoticeAdapter adapter;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_notice, container, false);

        noticeRecycler = view.findViewById(R.id.notice_recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        noticeRecycler.setLayoutManager(linearLayout);

        FirebaseRecyclerOptions<Notice> options = new FirebaseRecyclerOptions.Builder<Notice>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("notices"), Notice.class).build();

        adapter = new NoticeAdapter(options);

        noticeRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}