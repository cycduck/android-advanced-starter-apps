package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    public SimpleFragment() {
        // Required empty public constructor
    }

    // instantiating the fragment in the acitivity by providing a newinstance() factory method in the fragment
    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        // TODO: set the radioGroup onCheckedChange listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById((R.id.fragment_header));

                switch (index) {
                    case YES:
                        textView.setText((R.string.yes_message));
                        break;
                    case NO:
                        textView.setText(R.string.no_message);
                        break;
                }
            }
        });

        // Return the View for the fragment's UI
        return rootView;
    }
}
