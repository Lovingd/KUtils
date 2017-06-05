package cn.kutils.sample.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.kutils.FileSizeUtil;
import cn.kutils.boxing.Boxing;
import cn.kutils.boxing.impl.ui.BoxingActivity;
import cn.kutils.boxing.model.config.BoxingConfig;
import cn.kutils.boxing.model.entity.BaseMedia;
import cn.kutils.luban.Luban;
import cn.kutils.luban.OnMultiCompressListener;
import cn.kutils.sample.R;
import cn.kutils.view.nineimages.ImageInfo;
import cn.kutils.view.nineimages.NineGridView;
import cn.kutils.view.nineimages.preview.NineGridViewClickAdapter;

import static cn.kutils.FileSizeUtil.SIZETYPE_MB;

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
    @Bind(R.id.ng)
    NineGridView mNg;
    @Bind(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nineimages);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                //选择图片
                //构造配置参数
                append("开启相册选择图片");
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needCamera(R.mipmap.ic_launcher_round).needGif().withMaxCount(Integer.MAX_VALUE); // 支持gif，相机，设置最大选图数
//            .withMediaPlaceHolderRes(resInt) // 设置默认图片占位图，默认无
//            .withAlbumPlaceHolderRes(resInt) // 设置默认相册占位图，默认无
//             .withVideoDurationRes(resInt) // 视频模式下，时长的图标，默认无
//                初始化Boxing，构造Intent并启动
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, 999);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<BaseMedia> medias = Boxing.getResult(data);
        if (medias == null) {
            append("图片为空");
            return;
        }
        ArrayList<String> a = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        for (int i = 0; i < medias.size(); i++) {
            a.add(String.valueOf(FileSizeUtil.getFileOrFilesSize(medias.get(i).getPath(), SIZETYPE_MB))+ "Mb");
            files.add(new File(medias.get(i).getPath()));
        }
        append("选择图片" + medias.size() + "张,原图大小(依次:):" + a );

        Luban.get(this).load(files).putGear(Luban.FIRST_GEAR).launch(new OnMultiCompressListener() {//FIRST_GEAR  CUSTOM_GEAR THIRD_GEAR
            @Override
            public void onStart() {
                append("开始压缩,压缩时间会根据图片大小和数量相应增加");
            }

            @Override
            public void onSuccess(List<File> fileList) {
                ArrayList<String> c = new ArrayList<>();
                List<ImageInfo> list = new ArrayList<>();
                for (int i = 0; i < fileList.size(); i++) {
                    c.add(String.valueOf(FileSizeUtil.getFileOrFilesSize(fileList.get(i).getPath(), SIZETYPE_MB))+ "Mb");
                    list.add(new ImageInfo(fileList.get(i).getPath(), fileList.get(i).getPath()));
                }
                append("张图片压缩完成,大小依次:" + c );
                append("设置到九图预览控件");
                mNg.setAdapter(new NineGridViewClickAdapter(NineImagesAty.this, list));
                mNg.setSingleImageSize(250);
            }

            @Override
            public void onError(Throwable e) {
                append("压缩出错," + e.toString());
            }

        });
    }


    private void append(String str) {
        mTv.append(str + "\n");
    }
}
