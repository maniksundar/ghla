package com.ghla.library.authority;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        Title title = mTitles.get(position);
        LinearLayout linearLayout = (LinearLayout) holder.mView;
        addTitleView(linearLayout, title.text);
        for (Subtitle subtitle: title.subtitles){
            addSubtitleView(linearLayout, subtitle.text);
            for (Question question : subtitle.questions){
                addQuestionView(linearLayout, question.text);
            }
        }
    }

    void addTitleView (LinearLayout layout, String text) {
        LinearLayout ll = layout.findViewById(R.id.title);
        TextView tv = ll.findViewById(R.id.text);
        tv.setText(text);
    }
    void addSubtitleView (LinearLayout layout, String text) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subtitleLayout = vi.inflate(R.layout.report_content_subtitle, null);
        TextView tv = subtitleLayout.findViewById(R.id.text);
        tv.setText(text);
        layout.addView(subtitleLayout);
    }
    void addQuestionView (LinearLayout layout, String text) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View questionLayout = vi.inflate(R.layout.report_content_question, null);
        TextView tv = questionLayout.findViewById(R.id.text);
        tv.setText(text);
        layout.addView(questionLayout);
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public Title title;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
