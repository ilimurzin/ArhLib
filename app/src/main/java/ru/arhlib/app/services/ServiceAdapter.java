package ru.arhlib.app.services;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.arhlib.app.R;
import ru.arhlib.app.databinding.ServiceItemBinding;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private final List<Service> mValues;

    public ServiceAdapter() {
        mValues = new ArrayList<>();
        mValues.add(new Service(R.drawable.ic_catalog, R.string.catalog));
        mValues.add(new Service(R.drawable.ic_prolongation, R.string.prolongation));
        mValues.add(new Service(R.drawable.ic_contacts, R.string.contacts));
        mValues.add(new Service(R.drawable.ic_question, R.string.virtual_help));
        mValues.add(new Service(R.drawable.ic_lawyer, R.string.ask_lawyer));
        mValues.add(new Service(R.drawable.ic_about, R.string.about));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceItemBinding binding = ServiceItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.binding.setCallback(new ServiceCallback());
        holder.binding.setService(mValues.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ServiceItemBinding binding;

        public ViewHolder(ServiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
