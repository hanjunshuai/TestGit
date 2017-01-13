package hjs.shougang.com.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    /**
     * 显示一般AlertDialog
     *
     * @param v
     */
    public void showAD(View v) {
        new AlertDialog.Builder(this)
                .setTitle("删除数据")//设置标题
                .setMessage("确定删除数据吗！")//设置内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();//方法链调用
    }

    public void showLD(View v) {
        final String[] items = {"红", "红1", "红2", "红3"};

        new AlertDialog.Builder(this)
                .setTitle("颜色")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//which就是选中的position
                        //提示颜色
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        //移除dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 自定义dialog
     *
     * @param v
     */
    public void showCD(View v) {

        //动态加载布局文件，得到对应的view对象
        final View view = View.inflate(this, R.layout.dialog_view, null);
        //view是布局文件根标签的类型
        //得到独立子view
        final EditText name_et = (EditText) view.findViewById(R.id.et_dialog_name);
        final EditText pwd_et = (EditText) view.findViewById(R.id.et_dialog_name);

        new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //读取用户名和密码
                        String name = name_et.getText().toString();
                        String passwprd = pwd_et.getText().toString();
                        //提示
                        Toast.makeText(DialogActivity.this, name, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    /**
     * 显示圆形的dialog
     *
     * @param v
     */
    public void showPD(View v) {
        final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中...");

        //长时间工作不能在主线程做，得启动分线程
        new Thread() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {//在主线程执行
                        //runOnUiThread在分线程执行
                        //不能在分线程直接更新UI
                        Toast.makeText(DialogActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();

    }

    /**
     * 显示水平的dialog
     *
     * @param v
     */
    public void showPD2(View v) {
        //1、创建dialog对象
        final ProgressDialog pd = new ProgressDialog(this);
        //2、设置样式
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //3、显示
        pd.show();

        //4.启动分线程，加载数据，并显示进度，当加载完成移除dialog
        new Thread(new Runnable() {
            int count = 20;

            @Override
            public void run() {
                //设置最大进度
                pd.setMax(count);
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pd.setProgress(pd.getProgress() + 2);
                }
                pd.dismiss();
                startActivity(new Intent(DialogActivity.this, MenuActivity.class));
            }
        }).start();

    }

    public void showDateAD(View v) {
        //创建日历对象
        Calendar calendar = Calendar.getInstance();
        //得到当前的年月日
        final int year = calendar.get(Calendar.YEAR);//得到年份
        final int monthOfYear = calendar.get(Calendar.MONTH);//月
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);//得到日
        Log.e("TAG", year + "-" + monthOfYear + "-" + dayOfMonth);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Log.e("TAG", year + "--" + (monthOfYear+1) + "--" + dayOfMonth);
            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    public void showTimeAD(View v) {
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY); //得到小时
        int minute = c.get(Calendar.MINUTE); //得到分钟
        Log.e("TAG", hourOfDay + ":" + minute);

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e("TAG", hourOfDay + "::" + minute);
            }
        }, hourOfDay, minute, true).show();
    }


}
