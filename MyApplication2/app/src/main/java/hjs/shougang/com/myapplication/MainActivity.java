package hjs.shougang.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb_loading, pb_loading2;
    private SeekBar sb_loading;

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //滑杆移动
            Log.e("TAG", "滑杆移动");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//按下滑杆
            Log.e("TAG", "按下移动");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//离开滑杆
            Log.e("TAG", "离开移动");
            //1、得到SeekBar的进度
            int progress = sb_loading.getProgress();
            //2设置progressBar的进度
            pb_loading2.setProgress(progress);
            //3、判断是否达到最大值
            if (progress == sb_loading.getMax()) {
                //如果达到 设置pb_loading(INVISIBLE)不可见，但占用空间.(GONE)不可见，且不占空间
                pb_loading.setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            } else {
                //如果没有达到 设置pb_loading 显示
                pb_loading.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        pb_loading = (ProgressBar) findViewById(R.id.pb_loading);
        pb_loading2 = (ProgressBar) findViewById(R.id.pb_loading2);
        sb_loading = (SeekBar) findViewById(R.id.sb_loading);

        sb_loading.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
}
