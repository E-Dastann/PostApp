package com.example.postapp.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postapp.App;
import com.example.postapp.R;
import com.example.postapp.data.models.Post;
import com.example.postapp.data.remote.PostApiClient;
import com.example.postapp.databinding.FragmentFormBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;


public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private Post post;
    private static final int userID = 1;
    private static final int groupId = 41;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPost();
        initListener();
    }


    private void initListener() {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etText.getText().toString();
                String content = binding.etContent.getText().toString();
                if (getArguments() != null) {
                    updatePost(title, content);
                } else {
                    createPost(title, content);
                }
            }
        });
    }

    private void updatePost(String title, String content) {
        post.setContent(content);
        post.setTitle(title);
        App.api.updatePost(post.getId(), post).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    requireActivity().onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void createPost(String title, String content) {
        Post post = new Post(title, content, userID, groupId);
        App.api.createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    requireActivity().onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }

    private void getPost() {
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            post = (Post) bundle.getSerializable("key");
            binding.etContent.setText(post.getContent());
            binding.etText.setText(post.getTitle());
        }
    }
}