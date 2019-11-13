package edu.temple.bookcase;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BookDetailsFragment extends Fragment {
    String title;
    TextView Text;
    String author;
    ImageView image;
    String published;
    public static final String key = "Title";
    Book novels;

    public BookDetailsFragment() {
    }

    public static BookDetailsFragment newInstance(Book list) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            novels = getArguments().getParcelable(key);
        }
    }
    public void ShowBook(Book bookObj){
        String pictures = bookObj.getCoverURL();
        author = bookObj.getAuthor();
        title = bookObj.getTitle();
        published = bookObj.getPublished();
        Text.setText(" \"" + title + "\" ");
        Text.append(" " + author);
        Text.append(" " + published);
        Text.setTextSize(30);
        Text.setTextColor(Color.BLACK);
        Picasso.get().load(pictures).into(image);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        Text = view.findViewById(R.id.bookname);
        image = view.findViewById(R.id.bookpics);
        if(getArguments() != null) {
            ShowBook(novels);
        }
        return view;
    }

}
