package com.ghla.library.authority;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReportsCardViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Report> m_reports;
    private OnItemClickListener m_listener;
    private Context context;

    public ReportsCardViewAdapter(List<Report> reports, OnItemClickListener listener) {
        this.m_reports = reports;
        this.m_listener = listener;
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
        viewHolder.bind(m_reports.get(position), m_listener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        this.context = viewGroup.getContext();
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

    public interface OnItemClickListener {
        void onReportItemClick(Report item);
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    public ViewHolder(View v){
        super(v);
    }
    public void bind(final Report report, final ReportsCardViewAdapter.OnItemClickListener listener){

    }
}

class HeaderViewHolder extends ViewHolder{
    protected TextView vTitle;

    public HeaderViewHolder(View v) {
        super(v);
        vTitle = (TextView) v.findViewById(R.id.header_title);
    }

    @Override
    public void bind(Report report, ReportsCardViewAdapter.OnItemClickListener listener) {

    }
}

class CardViewHolder extends ViewHolder {
    protected CardView vCardView;
    protected TextView vTitle;
    protected ConstraintLayout vLayout;
    protected ImageView vReportImage;

    public CardViewHolder(View v) {
        super(v);
        vCardView = v.findViewById(R.id.report_card);
        vTitle = v.findViewById(R.id.report_title);
        vLayout = v.findViewById(R.id.report_layout);
        vReportImage = v.findViewById(R.id.report_image);
    }

    @Override
    public void bind(final Report report, final ReportsCardViewAdapter.OnItemClickListener listener) {
        vCardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onReportItemClick(report);
                    }
                }
        );
    }
}
