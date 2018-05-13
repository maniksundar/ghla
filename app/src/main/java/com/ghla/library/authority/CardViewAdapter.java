package com.ghla.library.authority;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Card> m_cards;
    private int m_imageSize;

    public CardViewAdapter(List<Card> cards) {
        this.m_cards = cards;
    }

    public CardViewAdapter(List<Card> cards, int size){
        this.m_cards = cards;
        m_imageSize = size;
    }

    @Override
    public int getItemCount() {
        return m_cards.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Card card = m_cards.get(position);
        CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
        cardViewHolder.vTitle.setText(card.getTitle());
        cardViewHolder.vLayout.setBackgroundColor(card.getColor());
        cardViewHolder.vReportImage.setImageResource(card.getImage());
        setViewHolderProperties(cardViewHolder);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.report_card_view, viewGroup, false);
        return new CardViewHolder(itemView);
    }

    // Other methods
    void setViewHolderProperties (CardViewHolder cardViewHolder){
        cardViewHolder.vReportImage.getLayoutParams().width = m_imageSize;
        cardViewHolder.vReportImage.getLayoutParams().height = m_imageSize;
    }
}
