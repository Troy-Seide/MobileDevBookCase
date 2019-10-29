package edu.temple.bookcase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookInterface {
    boolean currentWindow;
    BookDetailsFragment bookDeets;
    ViewPagerFragment viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentWindow= findViewById(R.id.pane_1) == null;
        bookDeets = new BookDetailsFragment();
        BookListFragment listFragment = new BookListFragment();
        viewPage = new ViewPagerFragment();

        if(!currentWindow){
            getFragment(listFragment, R.id.pane_1);
            getFragment(bookDeets, R.id.pane_2);
            //getFragment(viewPage, R.id.container_3);
        }
        else {
            getFragment(viewPage, R.id.pane_3);
        }
    }


        public void getFragment(Fragment fragment, int ID){
            getSupportFragmentManager().
                    beginTransaction().
                    replace(ID, fragment).
                    addToBackStack(null).
                    commit();
        }
    @Override
    public void bookPicked(String novelName) {
        bookDeets.Selection(novelName);
    }
}