package cn.kutils.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.boxing.Boxing;
import cn.kutils.boxing.impl.ui.BoxingActivity;
import cn.kutils.boxing.model.config.BoxingConfig;
import cn.kutils.boxing.model.config.BoxingCropOption;
import cn.kutils.boxing.model.entity.BaseMedia;
import cn.kutils.boxing.utils.BoxingFileHelper;
import cn.kutils.klog.KLog;

/**
 * 创建时间：2017/6/2  上午11:22
 * 创建人：赵文贇
 * 类描述：
 * 包名：cn.kutils.sample
 * 待我代码编好，娶你为妻可好。
 */
public class MediaUseAty extends AppCompatActivity {
    @Bind(R.id.bt_1)
    Button mBt1;
    @Bind(R.id.bt_2)
    Button mBt2;
    @Bind(R.id.bt_3)
    Button mBt3;
    private static final int REQUESTCODE_1 = 11, REQUESTCODE_2 = 22, REQUESTCODE_3 = 33;
    @Bind(R.id.rv)
    RecyclerView mRv;
    MyImageAdapter imageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaaty);
        ButterKnife.bind(this);
        imageAdapter = new MyImageAdapter(null);
        imageAdapter.isFirstOnly(false);
        imageAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL,false));
        mRv.setAdapter(imageAdapter);
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MediaUseAty.this, ImageBrowseActivity.class);
                intent.putExtra("position", position);
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i = 0; i < imageAdapter.getData().size(); i++) {
                    arrayList.add(imageAdapter.getData().get(i));
                }
                intent.putStringArrayListExtra("imagePath", arrayList);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.bt_1, R.id.bt_2, R.id.bt_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                //构造配置参数
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needCamera(R.mipmap.ic_launcher_round).needGif().withMaxCount(9); // 支持gif，相机，设置最大选图数
//            .withMediaPlaceHolderRes(resInt) // 设置默认图片占位图，默认无
//            .withAlbumPlaceHolderRes(resInt) // 设置默认相册占位图，默认无
//             .withVideoDurationRes(resInt) // 视频模式下，时长的图标，默认无
//                初始化Boxing，构造Intent并启动
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_1);

                break;
            case R.id.bt_2:
                //启动视频选择
                BoxingConfig videoConfig = new BoxingConfig(BoxingConfig.Mode.VIDEO).withVideoDurationRes(R.mipmap.ic_launcher_round);
                Boxing.of(videoConfig).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_2);

                break;
            case R.id.bt_3:
                String cachePath = BoxingFileHelper.getCacheDir(this);
                if (TextUtils.isEmpty(cachePath)) {
                    Toast.makeText(getApplicationContext(), R.string.boxing_storage_deny, Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri destUri = new Uri.Builder()
                        .scheme("file")
                        .appendPath(cachePath)
                        .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                        .build();
                BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri))
                        .withMediaPlaceHolderRes(R.mipmap.ic_launcher_round);
                Boxing.of(singleCropImgConfig).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        List<BaseMedia> medias = Boxing.getResult(data);
        //注意判断null
        if (medias == null || medias.size() == 0) return;
        for (BaseMedia baseMedia : medias) {
            imageAdapter.addData(baseMedia.getPath());
        }
    }


    private class MyImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyImageAdapter(@Nullable List<String> data) {
            super(R.layout.item_photo, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {

            ImageView photoView = (ImageView) helper.getView(R.id.pv);

//            PhotoView photoView = new PhotoView(MediaUseAty.this);
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(MediaUseAty.this).load(item).into(photoView);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            photoViewAttacher.setZoomable(false);
            helper.addOnClickListener(R.id.pv);
        }
    }
}









/*

package com.bilibili.boxing.ui;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.BottomSheetBehavior;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.FrameLayout;
        import android.widget.ImageView;

        import com.bilibili.boxing.AbsBoxingActivity;
        import com.bilibili.boxing.AbsBoxingViewFragment;
        import com.bilibili.boxing.Boxing;
        import com.bilibili.boxing.BoxingMediaLoader;
        import com.bilibili.boxing.demo.R;
        import com.bilibili.boxing.model.config.BoxingConfig;
        import com.bilibili.boxing.model.entity.BaseMedia;
        import com.bilibili.boxing.presenter.PickerPresenter;
        import com.bilibili.boxing_impl.ui.BoxingBottomSheetFragment;
        import com.bilibili.boxing_impl.ui.BoxingViewActivity;

        import java.util.ArrayList;
        import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout mInsideBottomSheet;
    private ImageView mResultImg;
    private BaseMedia mMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        createToolbar();
        findViewById(R.id.inside_bs_btn).setOnClickListener(this);
        mResultImg = (ImageView) findViewById(R.id.media_result);
        mResultImg.setOnClickListener(this);
        mInsideBottomSheet = (FrameLayout) findViewById(R.id.content_layout);
        BoxingBottomSheetFragment fragment = (BoxingBottomSheetFragment) getSupportFragmentManager().
                findFragmentByTag(BoxingBottomSheetFragment.TAG);
        if (fragment == null) {
            fragment = BoxingBottomSheetFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, fragment, BoxingBottomSheetFragment.TAG).commit();

            BoxingConfig singleImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                    .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image).withAlbumPlaceHolderRes(R.drawable.ic_boxing_default_image);
            Boxing.of(singleImgConfig).setupFragment(fragment, new Boxing.OnBoxingFinishListener() {

                @Override
                public void onBoxingFinish(Intent intent, List<BaseMedia> medias) {
                    BottomSheetBehavior behavior = BottomSheetBehavior.from(mInsideBottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    if (medias != null && medias.size() > 0) {
                        BaseMedia media = mMedia = medias.get(0);
                        String path = media.getPath();
                        BoxingMediaLoader.getInstance().displayRaw(mResultImg, path, 1080, 720, null);
                    }
                }
            });
        } else {
            fragment.setPresenter(new PickerPresenter(fragment));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.inside_bs_btn:
                showFragment();
                break;
            case R.id.media_result:
                if (mMedia == null) {
                    return;
                }
                ArrayList<BaseMedia> medias = new ArrayList<>(1);
                medias.add(mMedia);
                Boxing.get().withIntent(this, BoxingViewActivity.class, medias).start(this, BoxingConfig.ViewMode.PREVIEW);
                break;
            default:
                break;
        }
    }

    private void showFragment() {
        if (mInsideBottomSheet != null) {
            BottomSheetBehavior behavior = BottomSheetBehavior.from(mInsideBottomSheet);
            if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }
    }


    private void createToolbar() {
        Toolbar bar = (Toolbar) findViewById(R.id.nav_top_bar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle(R.string.second_demo_title);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
 *
 */
