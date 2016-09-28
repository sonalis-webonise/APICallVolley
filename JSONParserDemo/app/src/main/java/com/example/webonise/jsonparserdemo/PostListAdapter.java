package com.example.webonise.jsonparserdemo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by webonise on 28/9/16.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    public final static String postid = "Post Id: ";
    public final static String id = "Id: ";
    public final static String uName = "Name: ";
    public final static String email = "Email Id: ";
    public final static String body = "Body: ";

    private List<UserPost> userPostList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtpost, txtid, txtname, txtemail, txtbody;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtpost = (TextView) itemView.findViewById(R.id.txtpost);
            txtid = (TextView) itemView.findViewById(R.id.txtid);
            txtname = (TextView) itemView.findViewById(R.id.txtname);
            txtemail = (TextView) itemView.findViewById(R.id.txtemail);
            txtbody = (TextView) itemView.findViewById(R.id.txtbody);
        }
    }

    public PostListAdapter(List<UserPost> userPostList) {
        this.userPostList = userPostList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserPost userPost = userPostList.get(position);
        holder.txtpost.setText(postid + String.valueOf(userPost.getPostId()));
        holder.txtid.setText(id + String.valueOf(userPost.getId()));
        holder.txtname.setText(uName + userPost.getName());
        holder.txtemail.setText(email + userPost.getEmail());
        holder.txtbody.setText(body + userPost.getBody());
    }

    @Override
    public int getItemCount() {
        return userPostList.size();
    }
}
