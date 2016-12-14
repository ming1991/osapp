package net.oschina.app.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import net.oschina.app.R;

public class ImageSwitcherActivity extends AppCompatActivity {
    private static final String TAG = "GallertTest2";
    private ImageSwitcher is;
    private Gallery gallery;
    private int downX, upX;// index = 0;
    //private ArrayList<Integer> imageRes = new ArrayList<Integer>();

    private int[] imageld = {R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh);

        final ImageSwitcher imageSwitcher = (ImageSwitcher)findViewById(R.id.image_switcher);

        //Grallery-画廊视图
        final Gallery gallery = (Gallery)findViewById(R.id.gallery);

        // 设置ViewFactory接口
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(ImageSwitcherActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT,ImageSwitcher.LayoutParams.WRAP_CONTENT));
                return iv;
            }
        });

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getX();// 取得按下时的坐标
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upX = (int) event.getX();// 取得松开时的坐标
                    int index = 0;
                    if (upX - downX > 100)// 从左拖到右，即看前一张
                    {
                        // 如果是第一，则去到尾部
                        if (gallery.getSelectedItemPosition() == 0)
                            index = gallery.getCount() - 1;
                        else
                            index = gallery.getSelectedItemPosition() - 1;
                    } else if (downX - upX > 100)// 从右拖到左，即看后一张
                    {
                        // 如果是最后，则去到第一
                        if (gallery.getSelectedItemPosition() == (gallery
                                .getCount() - 1))
                            index = 0;
                        else
                            index = gallery.getSelectedItemPosition() + 1;
                    }
                    // 改变gallery图片所选，自动触发ImageSwitcher的setOnItemSelectedListener
                    gallery.setSelection(index, true);
                    return true;
                }
                return false;
            }

        });


        //设置图片切换器的动画效果

       /* imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in)); // 设置淡入效果
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out)); // 设置淡出效果*/




        //设置Grallery关联的适配器：
        gallery.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if(convertView == null){
                    imageView = new ImageView(ImageSwitcherActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setLayoutParams(new Gallery.LayoutParams(180,135));

                   /* TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
                    imageView.setBackgroundResource(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));*/

                    imageView.setPadding(5, 0, 5, 0);
                }else {
                    imageView = (ImageView)convertView;
                }
                imageView.setImageResource(imageld[position]); // imageld 是一个int[]{} 里面包含了所显示的图片ID => private int imageld[] = new int[] { R.drawable.img01, R.drawable.img02.....}
                return imageView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return imageld.length;
            }
        });


        //设置监听事件:

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int postion, long arg3) {
                imageSwitcher.setImageResource(imageld[postion]); // 根据图片索引获取图片ID，并设置给ImageSwitcher
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }


}
