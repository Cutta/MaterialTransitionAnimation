package com.cunoraz.mta;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by cuneytcarikci on 02/11/2016.
 *
 */

public class MyCardView extends RelativeLayout {
    CardView cardView;

    public MyCardView(Context context) {
        super(context);
        initialize(context);
        EventBus.getDefault().register(this);
    }

    private void initialize(Context context) {
        View root = inflate(context, R.layout.item_list, this);
        cardView = (CardView) root.findViewById(R.id.item_root);
    }

    @Subscribe
    public void onMessage(ScrollEvent event) {
        int margin = event.getMargin();
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0,margin,0,0);
        cardView.setLayoutParams(params);
    }

}
