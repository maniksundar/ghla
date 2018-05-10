package com.ghla.library.authority;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReportsCardViewAdapter extends RecyclerView.Adapter<ReportsCardViewAdapter.CardViewHolder>  {

        private List<Report> m_reports;
        private Context context;

        public ReportsCardViewAdapter() {
            DummyDataGenerator dummyDataGenerator = new DummyDataGenerator();
            this.m_reports = dummyDataGenerator.getReports();
        }

        @Override
        public int getItemCount() {
            return m_reports.size();
        }

    @Override
        public void onBindViewHolder(final ReportsCardViewAdapter.CardViewHolder CardViewHolder, int i) {
            Report report = this.m_reports.get(i);
            CardViewHolder.vTitle.setText(report.getTitle());
            CardViewHolder.vConstraintLayout.setBackgroundColor(report.getColor(context));
        }

        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            this.context = viewGroup.getContext();
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.report_card_view2, viewGroup, false);

            return new CardViewHolder(itemView);
        }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitle;
        protected ConstraintLayout vConstraintLayout;

        public CardViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.report_title);
            vConstraintLayout = (ConstraintLayout) v.findViewById(R.id.report_layout);
        }
    }
}
