package org.houxg.monkeyhey.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.houxg.monkeyhey.R;
import org.houxg.monkeyhey.component.ImproveTest;
import org.houxg.monkeyhey.util.ActivityStack;
import org.houxg.monkeyhey.util.BaseActivity;
import org.houxg.monkeyhey.util.TimeTool;
import org.houxg.monkeyhey.util.TitleTool;
import org.houxg.monkeyhey.util.ToastTool;
import org.houxg.monkeyhey.util.dialog.BaseDialog;
import org.houxg.monkeyhey.util.dialog.MessageDialog;
import org.houxg.monkeyhey.util.logger.Log;
import org.houxg.monkeyhey.util.logger.LogView;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements ActivityStack.ActivityRule, View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    LogView logView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main); //this must in front of super.onCreate(), so it can set title
        super.onCreate(savedInstanceState);

        //add a button on the right
        TitleTool.addAction(this, R.id.btn_add, "A", this);

        ButterKnife.inject(this);   //better to put it in last so it can inject to the view that added by code;
        logView = (LogView) findViewById(R.id.logview);
        Log.setViewLogger(logView);
    }

    @Override
    protected void onDestroy() {
        Log.setViewLogger(null);
        super.onDestroy();
    }

    @InjectView(R.id.edit_num_a)
    EditText textA;
    @InjectView(R.id.edit_num_b)
    EditText textB;
    @InjectView(R.id.edit_num_c)
    EditText textC;
    @InjectView(R.id.edit_num_d)
    EditText textD;

    @OnClick({R.id.btn_show_message_dialog, R.id.btn_show_toast, R.id.btn_try_log})
    void onClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_show_message_dialog:
                showDialog();
                break;
            case R.id.btn_show_toast:
                ToastTool.show(this, "it's a toast " + TimeTool.getyyyyMMdd(Calendar.getInstance()) + " " + TimeTool.getHHmmss(Calendar.getInstance()));
                break;
            case R.id.btn_try_log:
                logView.setText("");
                Log.e(TAG, "cal start!");   //you can check your Leancloud Class now;
                String a = textA.getText().toString();
                String b = textB.getText().toString();
                String c = textC.getText().toString();
                String d = textD.getText().toString();
                float[] temp = check(a, b, c, d);
                if (temp == null) {
                    return;
                }
                ImproveTest test = new ImproveTest();
                long lastTime = System.currentTimeMillis();
                test.cal(temp);
                Log.e(TAG, "cal end! find " + test.expCnt + " exp, use time:" + (System.currentTimeMillis() - lastTime) + "ms");
                break;
        }
    }

    private float[] check(String a, String b, String c, String d) {
        if (a.equals("") || b.equals("") || c.equals("") || d.equals("")) {
            toast("不能输入空");
            return null;
        }
        float[] res = new float[4];
        try {
            res[0] = Float.parseFloat(a);
            res[1] = Float.parseFloat(b);
            res[2] = Float.parseFloat(c);
            res[3] = Float.parseFloat(d);
        } catch (NumberFormatException ex1) {
            toast("输入错误！");
            return null;
        }
        for (float val : res) {
            if (val <= 0 || val > 13) {
                toast("输入点数应在1-13之间");
                return null;
            }
        }
        return res;
    }

    private void showDialog() {
        new MessageDialog.Builder(this)
                .setTitle("Message")
                .setMessage("You got me!")
                .setPositiveButton("confirm", new BaseDialog.OnButtonClickListener() {
                    @Override
                    public void onClick(BaseDialog dialog, int witch) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new BaseDialog.OnButtonClickListener() {
                    @Override
                    public void onClick(BaseDialog dialog, int witch) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    protected ActivityStack.ActivityRule getActivityRule() {
        return this;
    }

    @Override
    public boolean onPop(Activity prev, Activity now) {
        if (prev == null) {
            startActivity(MainActivity.class);  //loop
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        ToastTool.show(this, "Hey! it's an action");
    }
}
