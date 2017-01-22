package act.huanxin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyphenate.chat.EMClient;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/22 0022.
 * 开频界面
 */

public class ShapeActivity extends AppCompatActivity {

    private final static int time = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        init();
    }

    private void init() {
        //在分线程中进行睡眠
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (EMClient.getInstance().isLoggedInBefore()) {
                    //拿到当前的时间
                    long start = new Date().getTime();

                } else {

                }
            }
        }).start();
    }

}
