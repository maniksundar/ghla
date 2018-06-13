package com.ghla.library.authority;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ghla.library.authority.ReportContentFragment.OnListFragmentInteractionListener;
import org.greenrobot.eventbus.EventBus;
import java.util.List;

public class MyReportContentRecyclerViewAdapter extends RecyclerView.Adapter<MyReportContentRecyclerViewAdapter.ViewHolder> {

    private Report mReport;
    private final List<Title> mTitles;
    private final OnListFragmentInteractionListener mListener;

    public MyReportContentRecyclerViewAdapter(Report report, OnListFragmentInteractionListener listener) {
        mReport = report;
        mTitles = report.getReportContent();
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
        addTitleView(linearLayout, position+1 + ". " + title.text);
        //Dynamically add the subtitle and question views.
        for (Subtitle subtitle: title.subtitles){
            addSubtitleView(linearLayout, subtitle.text);
            for (Question question : subtitle.questions){
                addQuestionView(linearLayout, question);
            }
        }
        if(position == mTitles.size()-1){
            addSubmitButtonToLastTitle(linearLayout);
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
    void addQuestionView (LinearLayout layout, Question question) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View questionLayout = vi.inflate(R.layout.report_content_question, null);
        TextView tv = questionLayout.findViewById(R.id.text);
        tv.setText(question.text);
        EditText answerEdit = questionLayout.findViewById(R.id.answerEdit);
        answerEdit.addTextChangedListener(new AnswerWatcher(question));
        layout.addView(questionLayout);
    }

    void addSubmitButtonToLastTitle(LinearLayout layout) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View buttonLayout = vi.inflate(R.layout.report_content_submit, null);
        Button button = buttonLayout.findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ready to submit the form
                System.out.println(mTitles);
                mReport.setReportContent(mTitles);
                // Send an event that the Membership report can be submitted.
                EventBus.getDefault().post(new Events(mReport, App.getContext().getString(R.string.submitMembershipReport)));
            }
        });
        layout.addView(buttonLayout);
    }

    void setAnswer(Object object, Integer answer){
        if (object.getClass() == Question.class){
            Question obj = (Question) object;
            obj.answer = answer;
        } else if (object.getClass() == Question.class){
            Subtitle obj = (Subtitle) object;
            obj.answer = answer;
        } else if (object.getClass() == Question.class){
            Title obj = (Title) object;
            obj.answer = answer;
        }
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

    class AnswerWatcher implements TextWatcher {

        Object dataModel;

        AnswerWatcher(Object dataModel){
            this.dataModel = dataModel;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            setAnswer(dataModel, Integer.parseInt(s.toString()));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
