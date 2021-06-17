package com.example.messedup.notice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messedup.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;


public class NoticeAdapter extends FirebaseRecyclerAdapter<Notice, NoticeAdapter.ViewHolder> {


    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<Notice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position, @NonNull Notice model) {

        if(model.getImage() == null || model.getImage().isEmpty()) {
            holder.noticeImage.setVisibility(View.GONE);
        }

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            holder.deleteNoticeBtn.setVisibility(View.GONE);
        }

        Picasso.get().load(model.getImage()).into(holder.noticeImage);
        holder.postTime.setText(model.getTime());
        holder.postDate.setText(model.getDate());
        holder.adminName.setText(model.getauthor());
        holder.showTitle.setText(model.getTitle());
        holder.showText.setText(model.getDesc());


        // delete notice from database
        holder.deleteNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are your sure to delete this Notice?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "DELETE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FirebaseStorage.getInstance().getReference().child("noticeImages")
                                        .child("notice" + model.getDate() + "-" + model.getTime())
                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        FirebaseDatabase.getInstance().getReference().child("notices")
                                                .child(getRef(position).getKey())
                                                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(v.getContext(), "Notice deleted.", Toast.LENGTH_SHORT).show();
                                                ((Activity)holder.postTime.getContext()).finish();
                                                holder.postTime.getContext().startActivity(new Intent(holder.showText.getContext(), ManageNotice.class));
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(v.getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                                        Log.d("jayant", e.toString());
                                    }
                                });

                                notifyItemRemoved(position);
                            }
                        }
                );

                builder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );

                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(dialog != null)
                    dialog.show();

            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView showTitle, showText, postDate, postTime, adminName;
        ImageView noticeImage;
        FloatingActionButton deleteNoticeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            showTitle = itemView.findViewById(R.id.show_notice_title);
            showText = itemView.findViewById(R.id.show_notice_text);
            noticeImage = itemView.findViewById(R.id.notice_image);
            postDate = itemView.findViewById(R.id.post_date);
            postTime = itemView.findViewById(R.id.post_time);
            adminName = itemView.findViewById(R.id.admin_name);
            deleteNoticeBtn = itemView.findViewById(R.id.delete_notice_btn);

        }
    }


}