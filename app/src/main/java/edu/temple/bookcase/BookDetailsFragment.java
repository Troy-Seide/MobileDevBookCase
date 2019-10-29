package edu.temple.bookcase;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BookDetailsFragment extends Fragment {

    TextView Text;
    String selected;
    public static final String key = "Title";

    public BookDetailsFragment() {
    }

    public static BookDetailsFragment newInstance(String novel) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(key, novel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            selected = getArguments().getString(key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        Text = view.findViewById(R.id.tvTitle);
        //Text.setText(selected);
        Selection(selected);
        return view;
    }


    public void Selection (String selectedItem){

        Text.setText(selectedItem);
    }

}
