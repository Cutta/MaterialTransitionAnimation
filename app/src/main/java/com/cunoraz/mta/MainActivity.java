package com.cunoraz.mta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClick {

    ArrayList<Item> items;
    RecyclerView recyclerView;
    LinearLayout headerView;
    AppBarLayout appBarLayout;

    AppBarStateChangeListener.State actualState;

    boolean isOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setRecycle();

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                actualState = state;
                if (state == State.IDLE) {
                    headerView.post(new Runnable() {

                        @Override
                        public void run() {
                            closeEffect(headerView);
                        }
                    });
                } else if (state == State.EXPANDED) {
                    headerView.post(new Runnable() {
                        @Override
                        public void run() {
                            openEffect(headerView);
                        }
                    });
                }
            }
        });

        final LinearLayoutManager llayoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    EventBus.getDefault().post(new ScrollEvent(0));
                } else
                    EventBus.getDefault().post(new ScrollEvent(1));

            }
        });

    }

    private void setRecycle() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(getItems());
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        headerView = (LinearLayout) findViewById(R.id.header);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
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

    private void openEffect(final View myView) {

        if (isOpen)
            return;

        Animation bottomUp = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.grow_from_center);
        myView.startAnimation(bottomUp);
        myView.setVisibility(View.VISIBLE);
        isOpen = true;

    }

    private void closeEffect(final View myView) {
        if (!isOpen)
            return;

        Animation bottomUp = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.shring_to_center);
        bottomUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                myView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        myView.startAnimation(bottomUp);
        myView.setVisibility(View.INVISIBLE);
        isOpen = false;

    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("item", items.get(position));
        ActivityOptionsCompat options =

                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, getString(R.string.transition_item));
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    public ArrayList<Item> getItems() {
        items = new ArrayList<>();
        items.add(new Item("Bottom Navigation", "#F1D05D"));
        items.add(new Item("Buttons", "#F1D05D"));
        items.add(new Item("Cards", "#00bfff"));
        items.add(new Item("Chips", "#00bfff"));
        items.add(new Item("Data Tables", "#ff33cc"));
        items.add(new Item("Dialogs", "#ff33cc"));
        items.add(new Item("Dividers", "#59D5B8"));
        items.add(new Item("Expansion Panels", "#59D5B8"));
        items.add(new Item("Grid Lists", "#F1D05D"));
        items.add(new Item("Lists", "#F1D05D"));
        items.add(new Item("Cards", "#00bfff"));
        items.add(new Item("Chips", "#00bfff"));
        items.add(new Item("Bottom Navigation", "#F1D05D"));
        items.add(new Item("Buttons", "#F1D05D"));
        items.add(new Item("Data Tables", "#ff33cc"));
        items.add(new Item("Dialogs", "#ff33cc"));
        items.add(new Item("Dividers", "#59D5B8"));
        items.add(new Item("Expansion Panels", "#59D5B8"));
        items.add(new Item("Grid Lists", "#F1D05D"));
        items.add(new Item("Lists", "#F1D05D"));

        return items;
    }
}
