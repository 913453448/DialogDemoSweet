package com.cisetech.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cisetech.dialogdemo.dialog.WechatFragment;

public class MainActivity extends AppCompatActivity {
    private int screenWidth;
    private ImageView id_iv;
    private ProgressBar pb;

    private ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm=getWindowManager();
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth=outMetrics.widthPixels;
        id_iv= (ImageView) findViewById(R.id.id_iv);
        pb= (ProgressBar) findViewById(R.id.pb);
        img1= (ImageView) findViewById(R.id.id_img1);
        img2= (ImageView) findViewById(R.id.id_img2);
        Glide.with(this).load("http://img0.bdstatic.com/img/image/shouye/xinshouye/dongman928.jpg")
                .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                return false;
            }

            @Override
            public boolean onResourceReady(final GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                Log.e("TAG", "onResourceReady: target-------->"+target );
                Log.e("TAG", "onResourceReady: glideDrawable-------->"+glideDrawable );
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(MainActivity.this).load("http://img.gaoxiaogif.cn/GaoxiaoGiffiles/images/2016/01/19/weizheshiwuzhuangquandegougou.gif").
                                    centerCrop().placeholder(glideDrawable).
                                    crossFade(1000).diskCacheStrategy(DiskCacheStrategy.SOURCE).
                                    listener(new RequestListener<String, GlideDrawable>() {
                                        @Override
                                        public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                                            pb.setVisibility(View.GONE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                                            pb.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).
                                    into(id_iv);
                        }
                    },200);
                return false;
            }
        }).
        transform(new MyTransformtion(this)).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                placeholder(new ColorDrawable(Color.BLACK))
                .dontAnimate().into(id_iv);

        //createLoadingDialog(this).show();xxxxxxx
        showWechatDialog();
//        createLoadingDialog(this).show();
    }

    private void startAnima() {
        ScaleAnimation a=new ScaleAnimation(0.5f,1,0.5f,1, Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF);
        ScaleAnimation a2=new ScaleAnimation(0.5f,1,0.5f,1, Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF);
        a.setDuration(1000);
        a.setFillAfter(true);
        a2.setDuration(1000);
        a2.setFillAfter(true);
        a2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img2.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img1.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return flags[0]?input:1-input;
            }
        });
        a2.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return flags[1]?input:1-input;
            }
        });
        img1.startAnimation(a);
        img2.startAnimation(a2);
    }
    private boolean[] flags={true,false};
    public void startImg(View view){
       /* view.setEnabled(false);
            flags[0]=!flags[0];
            flags[1]=!flags[1];
        startAnima();*/
        showWechatDialog();
    }

    /**
     * 仿微信Dialog
     * @param context
     * @return
     */
    public Dialog createLoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.wechat_dialog, null);// 得到加载view
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(v);// 设置布局
        /**
         * 设置Dialog的宽度为屏幕宽度的61.8%，高度为自适应
         */
        WindowManager.LayoutParams lp = loadingDialog.getWindow().getAttributes();
        lp.width= (int) (screenWidth*0.618f);
        lp.height=lp.WRAP_CONTENT;
        loadingDialog.getWindow().setAttributes(lp);
        return loadingDialog;
    }
    private WechatFragment wechatDialog;
    public void showWechatDialog(){
        if(wechatDialog!=null&&wechatDialog.getDialog()!=null&&wechatDialog.getDialog().isShowing()){
            wechatDialog.dismiss();
            return;
        }
        wechatDialog=new WechatFragment();
       // wechatDialog.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.loading_dialog);
        wechatDialog.show(getSupportFragmentManager(),"wechatDialog");
    }

    public class MyTransformtion extends BitmapTransformation{
        public MyTransformtion(Context context) {
            super(context);
        }
        @Override
        protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {
            Log.e("TAG", "transform: bitmap-------->"+bitmap );
            return FastBlur.doBlur(bitmap,10,true);
        }
        @Override
        public String getId() {
            return "com.cisetech.dialogdemo.MyTransformtion";
        }
    }
}
