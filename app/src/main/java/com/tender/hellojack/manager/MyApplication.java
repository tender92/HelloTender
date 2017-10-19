package com.tender.hellojack.manager;

import android.app.Application;
import android.content.Context;

import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;
import com.tender.hellojack.utils.imageloder.ImageLoaderUtil;
import com.tender.hellojack.utils.imageloder.UILImageLoader;

/**
 * Created by boyu on 2017/10/19.
 */

public class MyApplication extends Application {

    private IManager[] managers;

    @Override
    public void onCreate() {
        super.onCreate();

        //日志框架初始化
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        initManager();
        initImagePicker();
        initImageLoader(getApplicationContext());
    }

    /**
     * 初始化各Manager
     */
    private void initManager() {
        managers = new IManager[]{
                PrefManager.getInstance()
        };

        for (IManager manager : managers) {
            manager.init(getApplicationContext());
        }
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new UILImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    /**
     * ImageLoader 图片组件初始化
     * @param context
     */
    private void initImageLoader(Context context) {

//        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils.getOwnCacheDirectory(context, "CSDN_LQR/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
//                .discCacheFileCount(10) //缓存的文件数量
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
//                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(ImageLoaderUtil.options)//DisplayImageOptions.createSimple()
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
}