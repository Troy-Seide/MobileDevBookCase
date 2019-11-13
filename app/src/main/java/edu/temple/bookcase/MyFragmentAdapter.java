package edu.temple.bookcase;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;

public class MyFragmentAdapter extends BaseAdapter implements Filterable {
    ArrayList<Book> BD;
    Context context;

    public MyFragmentAdapter(Context context, ArrayList<Book> BD) {
        this.context = context;
        this.BD = BD;
    }

    @Override
    public int getCount() {
        return BD.size();
    }

    @Override
    public Filter getFilter(){
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(BD.get(position).getTitle());
        textView.setTextSize(18);
        return textView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return BD.get(position);
    }
}

