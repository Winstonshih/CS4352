package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    // Binding object for the fragment's layout. This helps access the views in the layout.
    private FragmentFirstBinding binding;

    // This method inflates the fragment's layout when it is created.
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout using the FragmentFirstBinding and return the root view.
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Called after the fragment's view has been created.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up a click listener for the "buttonFirst" button.
        // When clicked, it navigates to the SecondFragment using NavController.
        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );
    }

    // Clean up the binding object to avoid memory leaks when the view is destroyed.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}