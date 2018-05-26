package com.fskj.gaj;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class Test2Activity extends AppCompatActivity {
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = Test2Activity.this;
        setContentView(R.layout.activity_test2);
        ImageView imageView = (ImageView) findViewById(R.id.img);
        String url = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2243899287,398342213&fm=27&gp=0.jpg";
//        Glide.with(activity)
//                .load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2243899287,398342213&fm=27&gp=0.jpg")
////                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(10,0)))
////                    .bitmapTransform()
//                .into(imageView);

        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(50, 0, RoundedCornersTransformation.CornerType.ALL));
        Glide.with(this).load(url)
                .apply(bitmapTransform(multi))
                .into((ImageView) findViewById(R.id.img));
    }
}
