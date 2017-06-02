package cn.kutils.sample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.io.InputStream;
import java.util.List;


public class ImageBrowseAdapter extends PagerAdapter {

    PhotoViewAttacher mAttacher;
    private Context context;
    private List<String> imagePath;

    public ImageBrowseAdapter(Context context, List<String> urls) {
        this.context = context;
        this.imagePath = urls;
    }

    @Override
    public int getCount() {
        return imagePath.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        PhotoView photoView = new PhotoView(context);
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
//        new DownloadImageTask(context, imageView).execute(imagePath.get(position));
        Glide.with(context).load(imagePath.get(position)).into(photoView);
        view.addView(photoView);
        return photoView;
    }

//    private class DownloadImageTask extends BaseAsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(Context context, ImageView bmImage) {
//            super(context);
//            this.bmImage = bmImage;
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String path = urls[0];
//            Bitmap result = null;
//            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                //先设置为true，获取bitmap宽度、高度
//                options.inJustDecodeBounds = true;
//                InputStream in = new java.net.URL(path).openStream();
//                result = BitmapFactory.decodeStream(in, null, options);
//                in.close();
//                resetOptions(options);
//                //后设置为false，加载进内存显示
//                options.inJustDecodeBounds = false;
//                // InputStream在读取完之后就到结尾了，需要再次打开才能重新读取，否则下面的result将返回null
//                in = new java.net.URL(path).openStream();
//                result = BitmapFactory.decodeStream(in, null, options);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            if (result != null) {
//                bmImage.setImageBitmap(result);
//                // PhotoViewAttacher绑定ImageView
//                mAttacher = new PhotoViewAttacher(bmImage);
//            }
//        }
//    }

    /**
     * 设置inSampleSize参数
     *
     * @param options
     * @return
     */
    public void resetOptions(BitmapFactory.Options options) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels / 2;
        int height = dm.heightPixels / 2;
        options.inSampleSize = (options.outWidth / width > options.outHeight / height) ?
                options.outWidth / width : options.outHeight / height;
    }

}