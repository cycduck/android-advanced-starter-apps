package com.example.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    // To save the boolean value representing the Fragment display state, define a key for the Fragment state to use in the savedInstanceState Bundle
    static final String STATE_FRAGMENT = "state_of_fragment";
    // the value representing the Fragment display state
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.open_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

        // checks to see if the instance state of the Activity was saved for some reason, such as a configuration change
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            //  If the saved instance was not saved, it would be null. If the saved instance is not null, the code retrieves the Fragment state from the saved instance, and sets the Button
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
        // super in the bottom?
    }

    public void displayFragment() {
        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        // val fragment = ExampleFragment()
        // https://developer.android.com/guide/components/fragments

        // Get the FragmentManager and start a transaction.
        // To start a transaction, get an instance of FragmentManager using getSupportFragmentManager()
        // Use getSupportFragmentManager() (instead of getFragmentManager()) to instantiate the Fragment class so your app remains compatible with devices running system versions as low as Android 1.6
        FragmentManager fragmentManager = getSupportFragmentManager();
        // get an instance of FragmentTransaction that uses beginTransaction()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Fragment operations are wrapped into a transaction (similar to a bank transaction) so that all the operations finish before the transaction is committed for the final result
        // val fragmentManager = supportFragmentManager
        // val fragmentTransaction = fragmentManager.beginTransaction()

        // adds a new Fragment using the add() transaction method
        // 1st argument is the layout resource for the ViewGroup in which the Fragment is placed
        // 2nd argument is the fragment
        fragmentTransaction.add(
                R.id.fragment_container,
                simpleFragment
        ).addToBackStack(null).commit();
        // addToBackStack adds teh transaction to the back stack of Fragment transactions
        // the back stack is managed by Activity, allows user to return to the previous Fragment state w/ the back btn
        // commit() is used for the transaction to take effect

        // Update the Button text.
        mButton.setText(R.string.close);
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    public void closeFragment() {

        // again, get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // get the reference to the fragment view, have to convert the type cuz findFragmentById returns Fragment type
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);

        // check to see if the fragment is already showing
        if(simpleFragment != null) {
            // get an instance of FragmentTransaction that uses beginTransaction()
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // create and commit the transaction to remove the fragment.
            // Why don't you remove the container???
            fragmentTransaction.remove(simpleFragment).commit();
        }
        // Update the Button text.
        mButton.setText(R.string.open);
        // set boolean flag to indicate fragment is closed
        isFragmentDisplayed = false;
    }
}
