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
    private Button mSubmitButton;
    private Button mEditButton;
    private TextView mStatusTextView;


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
        addTitleView(linearLayout, title);
        //Dynamically add the subtitle and question views.
        for (Subtitle subtitle: title.subtitles){
            addSubtitleView(linearLayout, subtitle);
            for (Question question : subtitle.questions){
                addQuestionView(linearLayout, question);
            }
        }
        if(position == mTitles.size()-1){
            addButtons(linearLayout);
            if (mReport.getmStatus() != Report.Status.Submitted) {
                mSubmitButton.setVisibility(View.VISIBLE);
            } else{
                mStatusTextView.setVisibility(View.VISIBLE);
                mEditButton.setVisibility(View.VISIBLE);
            }
        }
    }

    void addTitleView (LinearLayout layout, Title title) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View titleLayout = vi.inflate(R.layout.report_content_title, null);
        TextView tv = titleLayout.findViewById(R.id.text);
        tv.setText(title.text);
        addEditText(titleLayout, title);
        layout.addView(titleLayout);
    }

    void addSubtitleView (LinearLayout layout, Subtitle subtitle) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View subtitleLayout = vi.inflate(R.layout.report_content_subtitle, null);
        TextView tv = subtitleLayout.findViewById(R.id.text);
        tv.setText(subtitle.text);
        addEditText(subtitleLayout, subtitle);
        layout.addView(subtitleLayout);
    }
    void addQuestionView (LinearLayout layout, Question question) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View questionLayout = vi.inflate(R.layout.report_content_question, null);
        TextView tv = questionLayout.findViewById(R.id.text);
        tv.setText(question.text);
        addEditText(questionLayout, question);
        layout.addView(questionLayout);
    }


    void addEditText(View layout, Answerable answerable){
        //If the field contains answerable true, only then add the EditText field.
        if(!answerable.answerable()) return;
        EditText answerEdit = layout.findViewById(R.id.answerEdit);
        answerEdit.setVisibility(View.VISIBLE);
        answerEdit.addTextChangedListener(new AnswerWatcher(answerable));
        if(answerable.getAnswer() != 0)answerEdit.setText(Integer.toString(answerable.getAnswer()));
    }

    void addButtons(LinearLayout layout){
        addSubmitButtonToLastTitle(layout);
        addEditButtonToLastTitle(layout);
    }

    void addSubmitButtonToLastTitle(LinearLayout layout) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View buttonLayout = vi.inflate(R.layout.report_content_submit, null);
        Button button = buttonLayout.findViewById(R.id.submit_button);
        mSubmitButton = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ready to submit the form
                System.out.println(mTitles);
                mReport.setReportContent(mTitles);
                mReport.setStatus(Report.Status.Submitted);
                // Send an event that the Membership report can be submitted.
                EventBus.getDefault().post(new Events(mReport, App.getContext().getString(R.string.submitMembershipReport)));
            }
        });
        layout.addView(buttonLayout);
    }

    void addEditButtonToLastTitle(LinearLayout layout) {
        LayoutInflater vi = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View buttonLayout = vi.inflate(R.layout.report_content_submit, null);
        TextView textView = buttonLayout.findViewById(R.id.status_text);
        mStatusTextView = textView;
        Button button = buttonLayout.findViewById(R.id.edit_button);
        mEditButton = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReport.setStatus(Report.Status.InProgress);
                mEditButton.setVisibility(View.GONE);
                mStatusTextView.setVisibility(View.GONE);
                mSubmitButton.setVisibility(View.VISIBLE);
            }
        });
        layout.addView(buttonLayout);
    }

    void setAnswer(Answerable answerable, Integer answer){
        answerable.setAnswer(answer);
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

        Answerable dataModel;

        AnswerWatcher(Answerable dataModel){
            this.dataModel = dataModel;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() > 0){
                setAnswer(dataModel, Integer.parseInt(s.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
