package com.ghla.library.authority;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReportsCardViewAdapter extends RecyclerView.Adapter<ReportsCardViewAdapter.CardViewHolder>  {

        private List<ContactInfo> contactList;

        public ReportsCardViewAdapter() {
                List result = new ArrayList();
                for (int i=1; i <= 30; i++) {
                    ReportsCardViewAdapter.ContactInfo ci = new ReportsCardViewAdapter.ContactInfo();
                    ci.name = ReportsCardViewAdapter.ContactInfo.NAME_PREFIX + i;
                    ci.surname = ReportsCardViewAdapter.ContactInfo.SURNAME_PREFIX + i;
                    ci.email = ReportsCardViewAdapter.ContactInfo.EMAIL_PREFIX + i + "@test.com";

                    result.add(ci);

                }
                this.contactList = result;
        }

        public ReportsCardViewAdapter(List<ContactInfo> contactList) {
            this.contactList = contactList;
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

    @Override
        public void onBindViewHolder(final ReportsCardViewAdapter.CardViewHolder CardViewHolder, int i) {
            ContactInfo ci = contactList.get(i);
            CardViewHolder.vName.setText(ci.name);
            CardViewHolder.vSurname.setText(ci.surname);
            CardViewHolder.vEmail.setText(ci.email);
            CardViewHolder.vTitle.setText(ci.name + " " + ci.surname);
        }

        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.report_card_view, viewGroup, false);

            return new CardViewHolder(itemView);
        }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public CardViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    public class ContactInfo {
        protected String name;
        protected String surname;
        protected String email;
        protected static final String NAME_PREFIX = "Name_";
        protected static final String SURNAME_PREFIX = "Surname_";
        protected static final String EMAIL_PREFIX = "email_";
    }
}
