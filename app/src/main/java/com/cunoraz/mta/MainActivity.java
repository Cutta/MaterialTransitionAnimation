package com.cunoraz.mta;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClick {

    ArrayList<Item> items;
    MyRecyclerView recyclerView;
    LinearLayout headerView;
    int margin = 5;

    //boolean closingProcess = false;
    //boolean openingProcess = false;

    AppBarStateChangeListener.State actualState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerView = (LinearLayout) findViewById(R.id.header);
        recyclerView = (MyRecyclerView) findViewById(R.id.recycler_view);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                actualState = state;
                if (state == State.IDLE) {
                    headerView.post(new Runnable() {

                        @Override
                        public void run() {
                            closeRevealEffect(headerView);
                        }
                    });
                } else if (state == State.EXPANDED) {
                    headerView.post(new Runnable() {
                        @Override
                        public void run() {
                            openRevealEffect(headerView);
                        }
                    });
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    //  Log.d("onScrollChange", " " + i + " " +i1+ " "+i2 + " "+i3 );
                    //      margin ++;
                    //    EventBus.getDefault().post(new ScrollEvent(margin));
                }
            });
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("onScrollStateChanged", "onScrollStateChanged: " + newState);
                margin++;
                EventBus.getDefault().post(new ScrollEvent(margin));
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(getItems());
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void openRevealEffect(final View myView) {
        //  Log.d("TAG:", "openRevealEffect " + closingProcess);
        //  if (closingProcess)
        //    return;
        //  closingProcess = false;
//        openingProcess = true;

        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //openingProcess = false;
            }
        });

        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void closeRevealEffect(final View myView) {
        //Log.d("TAG:", "closeRevealEffect " + openingProcess);
        // if (openingProcess)
        //   return;

        // closingProcess = true;
        //openingProcess = false;
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, finalRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
                //   closingProcess = false;
            }
        });

        anim.start();

    }

    @Subscribe
    public void onMessage(ScrollEvent event) {
        Log.d("onMessage", "onMessage: ");
    }

    @Override
    public void onClick(View v, int position) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra("item", items.get(position));
        startActivity(i);
    }

    public ArrayList<Item> getItems() {
        items = new ArrayList<>();
        items.add(new Item("Bottom Navigation", "#ffff66"));
        items.add(new Item("Buttons", "#ffff66"));
        items.add(new Item("Cards", "#00bfff"));
        items.add(new Item("Chips", "#00bfff"));
        items.add(new Item("Data Tables", "#ff33cc"));
        items.add(new Item("Dialogs", "#ff33cc"));
        items.add(new Item("Dividers", "#1ff966"));
        items.add(new Item("Expansion Panels", "#1ff966"));
        items.add(new Item("Grid Lists", "#ffff66"));
        items.add(new Item("Lists", "#ffff66"));
        items.add(new Item("Bottom Navigation", "#ffff66"));
        items.add(new Item("Buttons", "#ffff66"));
        items.add(new Item("Cards", "#00bfff"));
        items.add(new Item("Chips", "#00bfff"));
        items.add(new Item("Data Tables", "#ff33cc"));
        items.add(new Item("Dialogs", "#ff33cc"));
        items.add(new Item("Dividers", "#1ff966"));
        items.add(new Item("Expansion Panels", "#1ff966"));
        items.add(new Item("Grid Lists", "#ffff66"));
        items.add(new Item("Lists", "#ffff66"));

        return items;
    }
}
