package edu.temple.bookcase;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewPagerFragment extends Fragment {
    ViewPager viewPage;
    ViewPagerAdapter pager;
    BookDetailsFragment Bookdeets;
    Book novels;


    public ViewPagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        pager = new ViewPagerAdapter(getChildFragmentManager());
        viewPage = view.findViewById(R.id.viewPager);

        return view;
    }

    public void addPager(JSONArray bookArray) {
        for (int i = 0; i < bookArray.length(); i++) {
            try {
                pager.getItemPosition(i);
                pager.notifyDataSetChanged();
                JSONObject pagerData = bookArray.getJSONObject(i);

                novels = new Book(pagerData);
                Bookdeets = BookDetailsFragment.newInstance(novels);
                pager.add(Bookdeets);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        viewPage.setAdapter(pager);
    }
}

