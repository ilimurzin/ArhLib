package ru.arhlib.app.news;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.arhlib.app.R;

public class PostFragment extends Fragment {

    private OnListFragmentInteractionListener listener;

    public PostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        if (view instanceof RecyclerView) {
            PostViewModel viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
            RecyclerView recyclerView = (RecyclerView) view;
            PostAdapter adapter = new PostAdapter(listener);
            viewModel.getPosts().observe(this, adapter::submitList);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Post post);
    }
}
