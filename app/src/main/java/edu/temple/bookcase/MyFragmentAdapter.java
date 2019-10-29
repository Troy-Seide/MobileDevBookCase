package edu.temple.bookcase;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<BookDetailsFragment> BD;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        BD= new ArrayList<>();
    }


    public void add(BookDetailsFragment fragment){
        BD.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return BD.get(i);
    }

    @Override
    public int getCount() {
        return BD.size();
    }


}

