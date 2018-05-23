package com.ghla.library.authority;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghla.library.authority.LibraryFragment.OnListFragmentInteractionListener;

import java.util.List;
import java.util.Random;

public class MylibraryRecyclerViewAdapter extends RecyclerView.Adapter<MylibraryRecyclerViewAdapter.ViewHolder> {

    private final List<Library> mLibraries;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;

    public MylibraryRecyclerViewAdapter(List<Library> libraries, OnListFragmentInteractionListener listener) {
        mLibraries = libraries;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_library, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mLibrary = mLibraries.get(position);
        Library library = mLibraries.get(position);
        holder.vLayout.setBackgroundColor(getRandomLibraryColor());
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
        protected ConstraintLayout vLayout;
        protected  TextView vName;
        public Library mLibrary;

        public ViewHolder(View view) {
            super(view);
            vLayout = view.findViewById(R.id.library_layout);
            vName = view.findViewById(R.id.library_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + vName.getText() + "'";
        }
    }

    static int currentLibraryColor = 0;
    int getRandomLibraryColor(){
        int[] androidColors = mContext.getResources().getIntArray(R.array.libraryColors);
        return androidColors[currentLibraryColor++%androidColors.length];
    }
}
