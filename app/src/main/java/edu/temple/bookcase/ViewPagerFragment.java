package edu.temple.bookcase;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewPagerFragment extends Fragment {
    ViewPager viewPage;
    BookDetailsFragment Bookdeets;
    MyFragmentAdapter myFragAdapter;

    public ViewPagerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        viewPage = view.findViewById(R.id.viewPager);

        Resources resources = this.getResources();
        final String[] novels = resources.getStringArray(R.array.bookArr);

        Bookdeets = new BookDetailsFragment();
        viewPage = view.findViewById(R.id.viewPager);
        myFragAdapter = new MyFragmentAdapter(getChildFragmentManager());

        for (int i = 0; i < novels.length; i++) {
            Bookdeets = BookDetailsFragment.newInstance(novels[i]);
            myFragAdapter.add(Bookdeets);
        }
        viewPage.setAdapter(myFragAdapter);
        return view;
    }
}