package com.ghla.library.authority;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements TopMost {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EmailFragment m_emailFragment;
    private EditPasswordFragment m_editPasswordFragment;
    private  EditPhoneFragment m_editPhoneFragment;
    private Fragment m_topMost = this;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupMainViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // Rest of the non-default functions.

    private void setupMainViews() {
        View emailView = getView().findViewById(R.id.card_email);
        setTitleAndSubTitle(emailView, "Change email address", "Choose a different e-mail address for your account.");
        emailView.setOnClickListener(emailListener);
        View phoneView = getView().findViewById(R.id.card_phone);
        setTitleAndSubTitle(phoneView, "Phone number", "Add a phone number on your account");
        phoneView.setOnClickListener(phoneListener);
        View passwordView = getView().findViewById(R.id.card_password);
        setTitleAndSubTitle(passwordView, "Change password", "Choose a unique password to protected your account");
        passwordView.setOnClickListener(passwordListener);
    }

    private void setTitleAndSubTitle(View view, String title, String subtitle) {
        TextView titleView = view.findViewById(R.id.title);
        TextView subtitleView = view.findViewById(R.id.subtitle);
        titleView.setText(title);
        subtitleView.setText(subtitle);
    }

    View.OnClickListener emailListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
                  m_emailFragment = m_emailFragment == null ? new EmailFragment() : m_emailFragment;
                  switchToFragment(m_emailFragment,getString(R.string.FRAGMENT_EMAIL));
        }
    };
    View.OnClickListener phoneListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            m_editPhoneFragment = m_editPhoneFragment == null ? new EditPhoneFragment() : m_editPhoneFragment;
            switchToFragment(m_editPhoneFragment,getString(R.string.FRAGMENT_PHONE));
        }
    };
    View.OnClickListener passwordListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            m_editPasswordFragment = m_editPasswordFragment == null ? new EditPasswordFragment() : m_editPasswordFragment;
            switchToFragment(m_editPasswordFragment,getString(R.string.FRAGMENT_PASSWORD));
        }
    };
    public void switchToFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(this.getId(),fragment, tag);
        m_topMost = fragment;
        fragmentTransaction.commit();
    }

    @Override
    public Fragment getTopMostFragment() {
        return m_topMost;
    }

    @Override
    public void clearAll() {
        m_topMost = this;
    }
}
