package com.example.krishanroy.psychic_app_hw_roy_krishan.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.krishanroy.psychic_app_hw_roy_krishan.R;
import com.example.krishanroy.psychic_app_hw_roy_krishan.database.DrawableResources;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static final String MAIN_FRAGMENT_INT_KEY = "main argument string key";
    public static final String TAG = "something";
    private View rootView;
    private Button button;
    private int position;
    private FragmentInteractionInterface listener;
    private DrawableResources drawableResources;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(int position) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MAIN_FRAGMENT_INT_KEY, position);
        mainFragment.setArguments(bundle);
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(MAIN_FRAGMENT_INT_KEY);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionInterface) {
            listener = (FragmentInteractionInterface) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement this FragmentInteractionInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        button = rootView.findViewById(R.id.main_fragment_button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final View v = rootView;
        Spinner spinner = v.findViewById(R.id.main_fragment_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                listener.moveFromMainToChoiceFragment(0);
                                Log.d(TAG, "onClick position is: " + position);
                                break;
                            case 1:
                                listener.moveFromMainToChoiceFragment(1);
                                Log.d(TAG, "onClick position is: " + position);
                                break;
                            case 2:
                                listener.moveFromMainToChoiceFragment(2);
                                Log.d(TAG, "onClick position is: " + position);
                                break;
                        }
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}