package edu.temple.bookcase;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BookListFragment extends Fragment {
    Context context;
    ListView List;
    public BookInterface BI;

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_book_list, container, false);
        List = view.findViewById(R.id.BookListView);
        Resources resources = this.getResources();

        ArrayList<String> booklist = new ArrayList<>();
        booklist.add("To Kill a Mockingbird");
        booklist.add("The Great Gatsby");
        booklist.add("Of Mice and Men");
        booklist.add("The Silent Patient");
        booklist.add("Once More we Saw Stars");
        booklist.add("Mrs Everything");
        booklist.add("The Night Tiger");
        booklist.add("Daisy Jones & The Six");
        booklist.add("Underland");
        booklist.add("The Unwinding of the iracle");

        final String[] booksArr = resources.getStringArray(R.array.bookArr);
        List.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, booksArr));

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = (String) parent.getItemAtPosition(position);
                ((BookInterface)context).bookPicked(title);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookInterface) {
            BI = (BookInterface) context;
        }
        else {
            throw new RuntimeException(context.toString());
        }
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BI = null;
    }

    public interface BookInterface {
        void bookPicked(String title);
    }
}
