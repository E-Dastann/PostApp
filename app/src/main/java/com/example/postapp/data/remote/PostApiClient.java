package com.example.postapp.data.remote;

import com.example.postapp.App;
import com.example.postapp.data.models.Post;

import java.util.List;
import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApiClient {
    private PostApi api;

    public PostApiClient(PostApi api) {
        this.api = api;
    }

    public void getPosts(CustomRequestCallBack<List<Post>> callBack) {
       api.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body());
                }else {
                    callBack.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callBack.onFailure(t.getLocalizedMessage());

            }
        });
    }

    public interface CustomRequestCallBack<DATA> {
        void onSuccess(DATA data);

        void onFailure(String massage);
    }
}
