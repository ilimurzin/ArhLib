package ru.arhlib.app.services;

import androidx.recyclerview.widget.RecyclerView;
import ru.arhlib.app.databinding.ServiceItemBinding;

public class ServiceViewHolder  extends RecyclerView.ViewHolder {

    final ServiceItemBinding binding;

    public ServiceViewHolder(ServiceItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
