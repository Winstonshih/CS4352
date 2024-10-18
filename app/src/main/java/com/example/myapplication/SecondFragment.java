package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    // Binding object for the fragment's layout to easily access views in the layout
    private FragmentSecondBinding binding;

    // Inflates the layout for the fragment when it is created
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout using FragmentSecondBinding and return the root view
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Called after the fragment's view is created
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set a click listener on the "buttonSecond" button to navigate back to the FirstFragment
        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    // Clean up the binding object when the view is destroyed to prevent memory leaks
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}