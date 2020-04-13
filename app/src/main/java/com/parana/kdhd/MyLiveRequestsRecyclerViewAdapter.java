package com.parana.kdhd;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parana.kdhd.LiveRequestsFragment.OnListFragmentInteractionListener;
import com.parana.kdhd.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLiveRequestsRecyclerViewAdapter extends RecyclerView.Adapter<MyLiveRequestsRecyclerViewAdapter.ViewHolder> {

    private List<Request> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyLiveRequestsRecyclerViewAdapter(List<Request> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_liverequests, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.requestText.setText(mValues.get(position).getDetails());
        holder.nameText.setText(mValues.get(position).getUsername());
        holder.phoneText.setText(mValues.get(position).getPhNo());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameText;
        public final TextView phoneText;
        public final TextView requestText;
        public Request mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameText = (TextView) view.findViewById(R.id.nameText);
            phoneText = (TextView) view.findViewById(R.id.phoneText);
            requestText = (TextView) view.findViewById(R.id.requestText);
        }

    }
}
