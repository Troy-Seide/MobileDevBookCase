package edu.temple.bookcase;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BookListFragment extends Fragment {

    ListView lV;
    Context context;
    ArrayList<Book> list;
    Book novel;
    MyFragmentAdapter myFragadapter;
    private BookInterface selected;

    public BookListFragment() {
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookInterface) {
            selected=(BookInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);
        lV = v.findViewById(R.id.List);
        list = new ArrayList<>();

        return v;
    }

    public void getBooks(final JSONArray inventory){
        list.clear();
        for(int i = 0; i < inventory.length(); i++){
            try {
                list.add(new Book(inventory.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("Books", list.toString());
        refresh();
    }

    private void refresh(){
        myFragadapter = new MyFragmentAdapter(context, list);
        myFragadapter.notifyDataSetChanged();
        lV.setAdapter(myFragadapter);
        lV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                novel = list.get(position);
                ((BookInterface) context).bookSelected(novel);
            }
        });
    }
    public interface BookInterface {
        void bookSelected(Book bookObj);
    }
}
