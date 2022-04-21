package com.example.postapp.ui.post;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postapp.data.models.Post;
import com.example.postapp.data.remote.OnClickForm;
import com.example.postapp.databinding.ItemPostBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> posts = new ArrayList<>();
    private OnClickForm listener;
    private HashMap<Integer, String> hashMap = new HashMap<>();

    public PostAdapter(OnClickForm listener) {
        this.listener = listener;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding postBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(postBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(posts.get(position));
        holder.hashmap();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;

        public PostViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void onBind(Post post) {
            binding.tvUserId.setText(hashMap.get(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClickListener(post);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnItemLongClickListener(post);
                    return true;
                }
            });

        }

        public void hashmap() {
            hashMap.put(1,"Ельдорадо");
            hashMap.put(2,"Dastan");
            hashMap.put(3,"Gosha");
        }
    }
}
