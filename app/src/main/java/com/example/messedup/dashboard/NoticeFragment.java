package com.example.messedup.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.messedup.R;
import com.example.messedup.jokes.JokesActivity;
import com.example.messedup.meme.MemeActivity;
import com.example.messedup.user.AdminLogin;
import com.example.messedup.user.AdminSignup;
import com.example.messedup.notice.ManageNotice;
import com.example.messedup.notice.ShowNotice;
import com.google.firebase.auth.FirebaseAuth;

public class NoticeFragment extends Fragment {

    private TextView showNewsFeed, showAdminLogin, showAdminLogout, showAdminSignup;
    private LinearLayout showMyNotes, showBooks, showMemes, showJokes, showManageBooks, showManageNotice, manageLayout;

    private FirebaseAuth fAuth;

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

        showNewsFeed = view.findViewById(R.id.show_news_feed);
        showMyNotes = view.findViewById(R.id.show_my_notes);
        showBooks = view.findViewById(R.id.show_books);
        showMemes = view.findViewById(R.id.show_memes);
        showJokes = view.findViewById(R.id.show_jokes);
        showAdminLogin = view.findViewById(R.id.show_admin_login);
        showAdminLogout = view.findViewById(R.id.show_admin_logout);
        showAdminSignup = view.findViewById(R.id.show_admin_signup);
        showManageBooks = view.findViewById(R.id.show_manage_books);
        showManageNotice = view.findViewById(R.id.show_manage_notice);
        manageLayout = view.findViewById(R.id.manage_layout);

        fAuth = FirebaseAuth.getInstance();

        // handle view visibility
        if(fAuth.getCurrentUser() != null) {
            showAdminLogin.setVisibility(view.GONE);
        }
        else {
            showAdminSignup.setVisibility(view.GONE);
            manageLayout.setVisibility(view.GONE);
            showAdminLogout.setVisibility(view.GONE);
        }

        showNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), ShowNotice.class));
            }
        });

        showBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminFragment adminFragment = new AdminFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, adminFragment, "findThisFragment")
                        .addToBackStack(null).commit();
            }
        });

        showManageNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), ManageNotice.class));
            }
        });

        showAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), AdminLogin.class));

            }
        });

        showAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(view.getContext(), HomeActivity.class));
            }
        });

        showAdminSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), AdminSignup.class));
            }
        });



        showJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), JokesActivity.class));
            }
        });

        showMemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), MemeActivity.class));
            }
        });


        return view;
    }

}