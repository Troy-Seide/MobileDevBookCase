package edu.temple.bookcase;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<BookDetailsFragment> list;
    public ViewPagerAdapter(FragmentManager frag) {
        super(frag);
        list = new ArrayList<>();
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return ViewPagerAdapter.POSITION_NONE;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    public void add(BookDetailsFragment fragment){
        list.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }
}

