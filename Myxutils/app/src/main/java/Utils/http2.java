package Utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import static android.R.attr.animation;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */

public class http2 {

    public static void getdata(){

    }

    public static  void getoptions(ImageView imageView) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(220), DensityUtil.dip2px(220))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)

                .setUseMemCache(true)//设置使用缓存
                //加载失败后默认显示图片
                .build();

        x.image().bind(imageView, Url.ur2l,imageOptions);

    }}