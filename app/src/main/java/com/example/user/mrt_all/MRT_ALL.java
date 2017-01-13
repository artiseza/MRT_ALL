package com.example.user.mrt_all;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.user.mrt_all.Fragment.Record;
import com.example.user.mrt_all.Fragment.StationList;
import com.example.user.mrt_all.adapter.MyAdapter;
import com.example.user.mrt_all.Fragment.Mappic;
import com.example.user.mrt_all.Fragment.PriceSerch;
import com.example.user.mrt_all.Fragment.TimeSerch;
import com.example.user.mrt_all.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class MRT_ALL extends AppCompatActivity implements
        ViewPager.OnPageChangeListener
{

    private String[] titles = new String[]{"捷運路線圖","轉乘資訊","記帳備忘錄"}; // TabLayout中的標籤標題
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private MyAdapter myAdapter; // ViewPager的分配器



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt__all);



        initComponnent();
        initData();
    }


    private void initComponnent(){
        tabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
    }


    private void initData(){
        fragmentList = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putInt("idx", 0);
        Mappic myFmt = new Mappic();
        myFmt.setArguments(bundle);
        fragmentList.add(0, myFmt);


        Bundle bundle2 = new Bundle();
        bundle2.putInt("idx", 1);
        TimeSerch myFmt2 = new TimeSerch();
        myFmt2.setArguments(bundle2);
        fragmentList.add(1, myFmt2);

        Bundle bundle3 = new Bundle();
        bundle3.putInt("idx", 2);
        Record myFmt3 = new Record();
        myFmt3.setArguments(bundle3);
        fragmentList.add(2, myFmt3);

//        Bundle bundle4 = new Bundle();
//        bundle4.putInt("idx", 3);
//        Record myFmt4 = new Record();
//        myFmt4.setArguments(bundle4);
//        fragmentList.add(3, myFmt4);




        myAdapter = new MyAdapter(getSupportFragmentManager(), titles, fragmentList);
        viewPager.setAdapter(myAdapter);

        viewPager.setOffscreenPageLimit(10); // 設置ViewPager最大頁面個數
        viewPager.addOnPageChangeListener(this); // 設定Listener,切換頁面時,換title

        tabLayout.setTabMode(MODE_SCROLLABLE); //MODE_SCROLLABLE模式 :Tab標籤會無限滾
        tabLayout.setTabMode(TabLayout.MODE_FIXED); //MODE_FIXED模式 :Tab標籤會固定住


        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        toolbar.setTitle(titles[position]); //切換頁面時,換title
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
