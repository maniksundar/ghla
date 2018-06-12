package com.ghla.library.authority;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghla.library.authority.ReportContentFragment.OnListFragmentInteractionListener;
import com.ghla.library.authority.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyReportContentRecyclerViewAdapter extends RecyclerView.Adapter<MyReportContentRecyclerViewAdapter.ViewHolder> {

    private final List<Title> mTitles;
    private final OnListFragmentInteractionListener mListener;

    public MyReportContentRecyclerViewAdapter(List<Title> titles, OnListFragmentInteractionListener listener) {
        mTitles = titles;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reportcontent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitleView.setText(mTitles.get(position).text);
        holder.mSubtitleView.setText(mTitles.get(position).text);
        holder.mQuestionView.setText(mTitles.get(position).text);
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mSubtitleView;
        public final TextView mQuestionView;
        public Title title;

        public ViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.membership_title);
            mSubtitleView = view.findViewById(R.id.membership_subtitle);
            mQuestionView = view.findViewById(R.id.membership_question);
        }
    }
}
