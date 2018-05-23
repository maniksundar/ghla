package com.ghla.library.authority;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghla.library.authority.LibraryFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MylibraryRecyclerViewAdapter extends RecyclerView.Adapter<MylibraryRecyclerViewAdapter.ViewHolder> {

    private final List<Library> mLibraries;
    private final OnListFragmentInteractionListener mListener;

    public MylibraryRecyclerViewAdapter(List<Library> libraries, OnListFragmentInteractionListener listener) {
        mLibraries = libraries;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_library, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mLibrary = mLibraries.get(position);
        Library library = (Library)mLibraries.get(position);
        holder.vName.setText(library.getName());

        holder.vName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mLibrary);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLibraries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected  TextView vName;
        public Library mLibrary;

        public ViewHolder(View view) {
            super(view);
            vName = view.findViewById(R.id.library_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + vName.getText() + "'";
        }
    }
}
