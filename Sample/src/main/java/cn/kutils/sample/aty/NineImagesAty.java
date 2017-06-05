package cn.kutils.sample.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.boxing.Boxing;
import cn.kutils.boxing.impl.ui.BoxingActivity;
import cn.kutils.boxing.model.config.BoxingConfig;
import cn.kutils.boxing.model.entity.BaseMedia;
import cn.kutils.sample.R;
import cn.kutils.view.nineimages.ImageInfo;
import cn.kutils.view.nineimages.NineGridView;
import cn.kutils.view.nineimages.preview.NineGridViewClickAdapter;

/**
 * 创建时间：2017/6/5  下午2:28
 * 创建人：赵文贇
 * 类描述：九图预览以及图片压缩处理
 * 包名：cn.kutils.sample.aty
 * 待我代码编好，娶你为妻可好。
 */
public class NineImagesAty extends AppCompatActivity {
    @Bind(R.id.bt_1)
    Button mBt1;
    @Bind(R.id.bt_2)
    Button mBt2;
    @Bind(R.id.ng)
    NineGridView mNg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nineimages);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_1, R.id.bt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                //选择图片
                //构造配置参数
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needCamera(R.mipmap.ic_launcher_round).needGif().withMaxCount(Integer.MAX_VALUE); // 支持gif，相机，设置最大选图数
//            .withMediaPlaceHolderRes(resInt) // 设置默认图片占位图，默认无
//            .withAlbumPlaceHolderRes(resInt) // 设置默认相册占位图，默认无
//             .withVideoDurationRes(resInt) // 视频模式下，时长的图标，默认无
//                初始化Boxing，构造Intent并启动
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, 999);
                break;
            case R.id.bt_2:
                //压缩图片
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<BaseMedia> medias = Boxing.getResult(data);
        if (medias == null) return;
        List<ImageInfo> list = new ArrayList<>();
        for (int i = 0; i < medias.size(); i++) {
            list.add(new ImageInfo(medias.get(i).getPath(),medias.get(i).getPath()));
        }
        mNg.setAdapter(new NineGridViewClickAdapter(NineImagesAty.this, list));
        mNg.setSingleImageSize(250);
    }
}
