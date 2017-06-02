package cn.kutils.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建时间：2017/6/2  上午8:07
 * 创建人：赵文贇
 * 类描述：适配器实现瀑布流效果
 * 包名：cn.kutils.sample
 * 待我代码编好，娶你为妻可好。
 */
public class BeautyPicturesActivity extends AppCompatActivity {
    @Bind(R.id.rv_main)
    RecyclerView mRvMain;
    private MyAdapter mAdapter;
    private List<Integer> heightList = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MyAdapter(null);
        mAdapter.isFirstOnly(false);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvMain.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        mRvMain.setAdapter(mAdapter);

        List<String> l = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            if (i % 2 == 0) {
                l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496372638951&di=be636f1362f06d68b902b8115cda13dc&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxgs_150428%2Fdesk_005.jpg");
            } else if (i % 3 == 0) {
                l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496373786557&di=bc8e94b8e82224a71ea7ea518d06e9ec&imgtype=0&src=http%3A%2F%2Fh7.86.cc%2Fwalls%2F20151020%2F1024x768_3fe2e5a70003597.jpg");
            } else if (i % 4 == 0) {
                l.add("http://pic.qiantucdn.com/58pic/15/36/00/73b58PICgvY_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
            } else {
                l.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496373818671&di=d2fc21e4fbeec9792dfb53ff31c03093&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201701%2F23%2F10783ece0ff1eb32bf137ff9b7ba329e.jpg");
            }
            heightList.add(new Random().nextInt(300) + 200);
        }
        mAdapter.setNewData(l);
    }


    private class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_girls, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = (ImageView) helper.getView(R.id.iv);
            //设置高度
            int height = heightList.get(helper.getPosition());
            //得到控件的高度
            ViewGroup.LayoutParams layoutParams = (imageView).getLayoutParams();
            //设置高度
            layoutParams.height = height;
            //使用Glide加载图片
            Glide.with(BeautyPicturesActivity.this).load(item).into(imageView);
        }
    }
}
