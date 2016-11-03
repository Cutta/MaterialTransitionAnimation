package com.cunoraz.mta;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by cuneytcarikci on 02/11/2016.
 */

public class MyCardView extends RelativeLayout {

    CardView cardView;

    ValueAnimator increaseAnimation;
    ValueAnimator decreaseAnimation;

    public static final int POSITION_DELAY = 0;

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
        int position = event.getPosition();

        if (margin == 0) {

            if (increaseAnimation != null && increaseAnimation.isRunning())
                increaseAnimation.cancel();

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
            int marginBottom = layoutParams.bottomMargin;

            decreaseAnimation = ValueAnimator.ofInt(marginBottom, 0);
            decreaseAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
                    layoutParams.bottomMargin = (int) animation.getAnimatedValue();
                    setLayoutParams(layoutParams);
                }
            });
            decreaseAnimation.setDuration(150 + POSITION_DELAY * position);
            decreaseAnimation.start();

        } else {

            if (decreaseAnimation != null && decreaseAnimation.isRunning())
                decreaseAnimation.cancel();

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
            int marginBottom = layoutParams.bottomMargin;

            increaseAnimation = ValueAnimator.ofInt(marginBottom, getResources().getDimensionPixelSize(R.dimen.cardview_max_marginBottom));
            increaseAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
                    layoutParams.bottomMargin = (int) animation.getAnimatedValue();
                    setLayoutParams(layoutParams);
                }
            });
            increaseAnimation.setDuration(150 + POSITION_DELAY * position);
            increaseAnimation.start();

        }

    }

}
