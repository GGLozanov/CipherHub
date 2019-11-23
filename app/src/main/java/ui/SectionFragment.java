package ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cipherhub.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends Fragment {

    TextView title;
    TextView description;

    public SectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        title = view.findViewById(R.id.sectionTitle);
        description = view.findViewById(R.id.sectionDescription);

        title.setText(getArguments().getString("Title"));
        description.setText(getArguments().getString("Description"));
        //getActivity() method returns the context of the current activity the fragment is in

        return view;
    }

}
