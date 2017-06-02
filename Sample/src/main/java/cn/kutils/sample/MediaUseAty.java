package cn.kutils.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.boxing.Boxing;
import cn.kutils.boxing.impl.ui.BoxingActivity;
import cn.kutils.boxing.impl.ui.BoxingViewActivity;
import cn.kutils.boxing.model.config.BoxingConfig;
import cn.kutils.boxing.model.config.BoxingCropOption;
import cn.kutils.boxing.model.entity.BaseMedia;
import cn.kutils.boxing.utils.BoxingFileHelper;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaaty);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_1, R.id.bt_2, R.id.bt_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                //构造配置参数
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needCamera(R.mipmap.ic_launcher).needGif().withMaxCount(9); // 支持gif，相机，设置最大选图数
//            .withMediaPlaceHolderRes(resInt) // 设置默认图片占位图，默认无
//            .withAlbumPlaceHolderRes(resInt) // 设置默认相册占位图，默认无
//             .withVideoDurationRes(resInt) // 视频模式下，时长的图标，默认无
//                初始化Boxing，构造Intent并启动
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUESTCODE_1);

                break;
            case R.id.bt_2:
                //启动视频选择
                BoxingConfig videoConfig = new BoxingConfig(BoxingConfig.Mode.VIDEO).withVideoDurationRes(R.mipmap.ic_launcher);
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
                        .withMediaPlaceHolderRes(R.mipmap.ic_launcher);
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
    }
}
