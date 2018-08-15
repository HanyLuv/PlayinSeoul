package com.work.hany.playinseoul.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoderUtils {

    public static void lodeURI(ImageView imageView, String uri) {
        Glide.with(imageView)
                .load(uri)
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.downsampleOf(DownsampleStrategy.CENTER_INSIDE))
                .into(imageView);

    }

    public static void lodeURI(ImageView imageView, String uri, RequestOptions options) {
        Glide.with(imageView)
                .load(uri)
                .apply(options)
                .apply(RequestOptions.downsampleOf(DownsampleStrategy.CENTER_INSIDE))
                .into(imageView);

    }

}


