package com.ghla.library.authority;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ReportsCardViewAdapter extends RecyclerView.Adapter<ViewHolder> {

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
    public int getItemViewType(int position) {
        Report report = m_reports.get(position);
        return report.getType();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        setViewByType(viewHolder, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        this.context = viewGroup.getContext();
        System.out.println(new Membership());
        switch (viewType) {
            case Report.TYPE_HEADER: {
                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.report_section_header, viewGroup, false);

                return new HeaderViewHolder(itemView);
            }
            case ReportType.TYPE_REPORT: {
                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.report_card_view, viewGroup, false);

                return new CardViewHolder(itemView);
            }
            default:{
                throw new IllegalStateException("Unsupported view type");
            }
        }
    }

    private void setViewByType(ViewHolder viewHolder, int position) {

        int viewType = m_reports.get(position).getType();

        switch (viewType) {
            case ReportHeader.TYPE_HEADER: {
                ReportHeader header = (ReportHeader) m_reports.get(position);
                HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
                holder.vTitle.setText(header.toString());
                break;
            }
            case ReportType.TYPE_REPORT: {
                Report report = m_reports.get(position);
                CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
                cardViewHolder.vTitle.setText(report.getTitle());
                cardViewHolder.vLayout.setBackgroundColor(report.getColor(context));
                cardViewHolder.vReportImage.setImageResource(report.getImage(context));
                break;
            }
            default:{
                throw new IllegalStateException("Unsupported view type");
            }
        }
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    public ViewHolder(View v){
        super(v);
    }
}

class HeaderViewHolder extends ViewHolder{
    protected TextView vTitle;

    public HeaderViewHolder(View v) {
        super(v);
        vTitle = (TextView) v.findViewById(R.id.header_title);
    }

}

class CardViewHolder extends ViewHolder {
    protected TextView vTitle;
    protected ConstraintLayout vLayout;
    protected ImageButton vReportImage;

    public CardViewHolder(View v) {
        super(v);
        vTitle = (TextView) v.findViewById(R.id.report_title);
        vLayout = (ConstraintLayout) v.findViewById(R.id.report_layout);
        vReportImage = (ImageButton) v.findViewById(R.id.report_image);
    }
}
