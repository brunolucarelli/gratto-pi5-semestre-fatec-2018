package com.gratto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gratto.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WhyDoADonationFragment extends Fragment {


    public WhyDoADonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_whydoadonation, container, false);

        Button btnReturnHome = (Button) view.findViewById(R.id.btnReturnHome);
        btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FindFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
