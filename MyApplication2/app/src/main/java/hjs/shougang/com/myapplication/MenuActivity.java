package hjs.shougang.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class MenuActivity extends AppCompatActivity {
    private Button btn_test2_show_cm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
    }

    private void initView() {
        btn_test2_show_cm = (Button) findViewById(R.id.btn_test2_show_cm);

        //Activity已经实现这个方法
        btn_test2_show_cm.setOnCreateContextMenuListener(this);
    }

    /**
     * contextMenu
     * 1、如何触发menu的显示？长按某个视图（设置一个监听setOnCreateContextMenuListener）
     * 2、如何向menu中添加MenuItem?重写onCreateContextMenu
     * menu.add()
     * 3、选择某个MenuItem时响应？重写onContextItemSelected
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //添加菜单
        menu.add(0, 1, 0, "添加");
        menu.add(0, 4, 0, "删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * OptionMenu
     * 1、如何触发menu的显示？点击menu键
     * 2、如何向menu中添加MenuItem
     * 1)menu.add()
     * 2)菜单文件
     * 3、选择某个MenuItem时响应？重写onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //一、纯编码方法
        menu.add(0, 2, 0, "添加");
        menu.add(0, 3, 0, "删除");

        //二、菜单文件方式
        //1.得到菜单加载器对象
//        MenuInflater menuInflater = getMenuInflater();
        //2.加载菜单文件
//        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
