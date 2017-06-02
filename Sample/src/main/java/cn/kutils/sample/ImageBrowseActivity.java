package cn.kutils.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cn.kutils.sample.adapter.ImageBrowseAdapter;

/**
 * 创建时间：2017/6/2  下午1:58
 * 创建人：赵文贇
 * 类描述：图片预览的activity
 * 包名：cn.kutils.sample
 * 待我代码编好，娶你为妻可好。
 */
public class ImageBrowseActivity extends AppCompatActivity {
    // ViewPager对象
    private ViewPager_ mViewPager;
    // 原图url路径List
    private List<String> imagePath;
    // 当前显示的位置
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_view);
        // 获取参数
        this.position = getIntent().getIntExtra("position", 0);
        this.imagePath = getIntent().getStringArrayListExtra("imagePath");
        mViewPager = (ViewPager_) findViewById(R.id.images_view);
        // 设置左右两列缓存的数目
        mViewPager.setOffscreenPageLimit(2);
        // 添加Adapter
        PagerAdapter adapter = new ImageBrowseAdapter(this, imagePath);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);
    }
}
