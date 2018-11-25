package ru.arhlib.app.actual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import ru.arhlib.app.R;

public class ActualFragment extends Fragment {

    public ActualFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actual_fragment, container, false);

        if (view instanceof RecyclerView) {
            ActualViewModel viewModel = ViewModelProviders.of(this).get(ActualViewModel.class);
            RecyclerView recyclerView = (RecyclerView) view;
            ActualAdapter adapter = new ActualAdapter();
            viewModel.getActual().observe(this, adapter::submitList);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }
}
