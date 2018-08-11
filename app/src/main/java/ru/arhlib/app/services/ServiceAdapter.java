package ru.arhlib.app.services;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.arhlib.app.R;
import ru.arhlib.app.services.ServiceFragment.OnListFragmentInteractionListener;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private final List<Service> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ServiceAdapter(OnListFragmentInteractionListener listener) {
        mValues = new ArrayList<>();
        mValues.add(new Service(R.drawable.ic_catalog, R.string.catalog));
        mValues.add(new Service(R.drawable.ic_prolongation, R.string.prolongation));
        mValues.add(new Service(R.drawable.ic_contacts, R.string.contacts));
        mValues.add(new Service(R.drawable.ic_question, R.string.virtual_help));
        mValues.add(new Service(R.drawable.ic_lawyer, R.string.ask_lawyer));
        mValues.add(new Service(R.drawable.ic_about, R.string.about));
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIcon.setImageResource(mValues.get(position).icon);
        holder.mName.setText(mValues.get(position).name);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIcon;
        public final TextView mName;
        public Service mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIcon = view.findViewById(R.id.service_icon);
            mName = view.findViewById(R.id.service_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
