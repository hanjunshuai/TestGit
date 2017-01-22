package hjs.shougang.com.fragment_add;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFrag();
        switch23();
        intetn2();
    }

    private void intetn2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 5555);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView result = (TextView) findViewById(R.id.text);
        result.setText("requestCode=" + requestCode + "resultCode=" + resultCode);
    }

    private void initFrag() {
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();

        fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft;
        ft = fm.beginTransaction();
        //将f1、f2、f3添加进布局
        ft.add(R.id.fragment_frame, f1);
        ft.add(R.id.fragment_frame, f2);
        ft.add(R.id.fragment_frame, f3);

        ft.hide(f1);
        ft.hide(f2);
        ft.hide(f3);

        ft.commit();
    }


    public void switch21() {
        FragmentTransaction ft;
        ft = fm.beginTransaction();
        //显示f1隐藏f2、f3
        ft.show(f1);
        ft.hide(f2);
        ft.hide(f3);
        ft.commit();
    }

    public void switch22() {
        FragmentTransaction ft;
        ft = fm.beginTransaction();
        ft.show(f2);
        ft.hide(f1);
        ft.hide(f3);
        ft.commit();
    }

    public void switch23() {
        FragmentTransaction ft;
        ft = fm.beginTransaction();
        ft.show(f3);
        ft.hide(f2);
        ft.hide(f1);
        ft.commit();
    }
}
