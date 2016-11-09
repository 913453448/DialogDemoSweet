package com.cisetech.dialogdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.cisetech.dialogdemo.R;

/**
 * author：yinqingy
 * date：2016-11-01 22:20
 * blog：http://blog.csdn.net/vv_bug
 * desc：
 */

public class WechatFragment extends DialogFragment {
/*    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wechat_dialog,null);
    }*/
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog =getDialog();//获取Dialog
        WindowManager.LayoutParams attr = dialog.getWindow().getAttributes();//获取Dialog属性
        WindowManager wm= (WindowManager) dialog.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetric=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetric);
        attr.width= (int) (outMetric.widthPixels*0.618f);
        dialog.getWindow().setAttributes(attr);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.wechat_dialog, null);// 得到加载view
        Dialog loadingDialog = new Dialog(getActivity(), R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(v);// 设置布局
        return loadingDialog;
    }
}
