package com.example.postapp.data.remote;

import com.example.postapp.data.models.Post;

public interface OnClickForm {
    void OnItemClickListener(Post post);
    void OnItemLongClickListener(Post post);
}
