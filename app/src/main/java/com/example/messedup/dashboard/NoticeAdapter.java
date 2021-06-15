package com.example.messedup.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messedup.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;

public class NoticeAdapter extends FirebaseRecyclerAdapter<Notice, NoticeAdapter.ViewHolder> {


    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<Notice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position, @NonNull Notice model) {

        if(model.getImage() == null || model.getImage().isEmpty()) {
            holder.noticeImage.setVisibility(View.GONE);
        }

        Picasso.get().load(model.getImage()).into(holder.noticeImage);
        holder.postTime.setText(model.getTime());
        holder.postDate.setText(model.getDate());
        holder.showTitle.setText(model.getTitle());
        holder.showText.setText(model.getDesc());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView showTitle, showText, postDate, postTime;
        ImageView noticeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            showTitle = itemView.findViewById(R.id.show_notice_title);
            showText = itemView.findViewById(R.id.show_notice_text);
            noticeImage = itemView.findViewById(R.id.notice_image);
            postDate = itemView.findViewById(R.id.post_date);
            postTime = itemView.findViewById(R.id.post_time);
        }
    }


}