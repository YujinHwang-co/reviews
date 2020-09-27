package com.example.reviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// 메인페이지 java 파일
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    ArrayList<Integer> data = new ArrayList<Integer>();

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 3000;   // 타이머 시작 후 해당 시간에 작동(초기 웨이팅 타입)
    final long PERIOD_MS = 3000;  // 4초 주기로 작동

    // 검색돋보기
    ImageView btnsearchmag;

    // 하단바 버튼
    ImageButton btnHome;
    ImageButton btnSocial;
    ImageButton btnMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰페이저에 추가할 영화 포스터 추가
        data.add(R.drawable.main_view_movie1);
        data.add(R.drawable.main_view_movie2);
        data.add(R.drawable.main_view_movie3);

        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(this, data);
        viewPager.setAdapter(pagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentPage == data.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        // 돋보기 구현
        btnsearchmag = (ImageView) findViewById(R.id.search_magnifier);
        btnsearchmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent); // 액티비티 띄우기
            }
        });

        // 탭호스트 구현
        TabHost tabHost1 = (TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        // 첫번째 탭
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("movie_rating_ranking");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("영화평점순위");
        tabHost1.addTab(ts1);

        // 두번째 탭
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("movie_rating_ranking");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("영화추천순위");
        tabHost1.addTab(ts2);

        // 세번째 탭
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("movie_rating_ranking");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("영화신작");
        tabHost1.addTab(ts3);


        // 하단바 underbar_home 버튼 등록 및 리스너 구현
        btnHome = (ImageButton)findViewById(R.id.underbar_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "메인페이지 입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 하단바 underbar_social 버튼 등록 및 리스너 구현
        btnSocial = (ImageButton)findViewById(R.id.underbar_social);
        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocialActivity.class);
                startActivity(intent); // 액티비티 띄우기
            }
        });

        // 하단바 underbar_mypage 버튼 등록 및 리스너 구현
        btnMypage = (ImageButton)findViewById(R.id.underbar_mypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent); // 액티비티 띄우기

            }
        });
    }
}
