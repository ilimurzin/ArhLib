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

import com.google.android.material.snackbar.Snackbar;

import ru.arhlib.app.R;
import ru.arhlib.app.databinding.ActualFragmentBinding;

public class ActualFragment extends Fragment {

    public ActualFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActualFragmentBinding binding = ActualFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ActualViewModel viewModel = ViewModelProviders.of(this).get(ActualViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.actual_list);
        ActualAdapter adapter = new ActualAdapter();
        viewModel.actual.observe(this, adapter::submitList);
        viewModel.showErrorSnackbar.observe(this, (needToShow) -> {
            if (needToShow) {
                Snackbar.make(view, R.string.loading_error, Snackbar.LENGTH_SHORT).show();
                viewModel.showErrorSnackbar.postValue(false);
            }
        });
        recyclerView.setAdapter(adapter);
        binding.setViewModel(viewModel);

        return view;
    }
}
