package ru.arhlib.app.news;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.arhlib.app.R;

public class PostFragment extends Fragment {

    public PostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        if (view instanceof RecyclerView) {
            PostViewModel viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
            RecyclerView recyclerView = (RecyclerView) view;
            PostAdapter adapter = new PostAdapter();
            viewModel.getPosts().observe(this, adapter::submitList);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }
}
