package edu.temple.bookcase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookInterface {
    boolean currentWindow;
    BookDetailsFragment bookDeets;
    ViewPagerFragment viewPage;
    JSONArray books;
    String string;
    BookListFragment BLF;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.search);
        button = findViewById(R.id.Button);
        currentWindow = findViewById(R.id.pane_2) == null;
        bookDeets = new BookDetailsFragment();
        BLF = new BookListFragment();
        viewPage = new ViewPagerFragment();

        if(!currentWindow){
            getFragment(BLF, R.id.pane_1);
            getFragment(bookDeets, R.id.pane_2);
        }
        else {
            getFragment(viewPage, R.id.pane_3);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 string= editText.getText().toString();

                Website(string);

            }
        });
        button.performClick();
    }

    public void getFragment(Fragment fragment, int ID){
        getSupportFragmentManager().
                beginTransaction().
                replace(ID, fragment).
                addToBackStack(null).
                commit();
    }

    Handler handle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                books = new JSONArray((String) msg.obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(currentWindow) {
                viewPage.addPager(books);
            } else {
                BLF.getBooks(books);
            }
            return false;
        }
    });

    public void Website(final String s) {
        new Thread() {
            public void run() {
                try {
                    String link = "https://kamorris.com/lab/audlib/booksearch.php?search=" + s;
                    URL url = new URL(link);

                    BufferedReader BR = new BufferedReader(new InputStreamReader(url.openStream()));

                    StringBuilder builder = new StringBuilder();
                    String holder;
                    while ((holder = BR.readLine()) != null) {
                        builder.append(holder);
                    }

                    Message message = Message.obtain();
                    message.obj = builder.toString();
                    handle.sendMessage(message);
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    @Override
    public void bookSelected(Book bookObj) {
        bookDeets.ShowBook(bookObj);
    }
}
