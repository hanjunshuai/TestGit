package act.day_17_0210;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et_network_url, et_network_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_network_url = (EditText) findViewById(R.id.et_network_url);
        et_network_result = (EditText) findViewById(R.id.et_network_result);
    }


    /*
     * 使用httpUrlConnection提交get请求
     */

    /**
     * 1.显示ProgressDialog
     * 2.启动分线程
     * 3.在分线程，发送请求，得到响应数据
     * 1）得到path，并带上参数name=junshuai&age=21
     * 2）创建url对象
     * 3）打开连接，得到HttpURLConnection
     * 4）设置请求方式，连接超时，读取数据超时
     * 5）连接服务器
     * 6）发请求，得到响应数据
     * 得到响应码，必须是200才读取
     * 得到InputStream，并读取成String
     * 7)断开连接
     * 4.在主线程，显示结果，移除dialog
     */
    public void testConnectionGet(View v) {
        //1.显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求...");
        //2.启动分线程
        new Thread() {
            //3.在分线程，发送请求，得到响应数据
            public void run() {
                try {
                    //1）得到path，并带上参数name=junshuai&age=21
                    String path = et_network_url.getText().toString() + "?name=junshuai&age=21";
                    // 2）创建url对象
                    URL url = new URL(path);
                    //3）打开连接，得到HttpURLConnection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //4）设置请求方式，连接超时，读取数据超时
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(6000);
                    //5）连接服务器
                    connection.connect();
                    // 6）发请求，得到响应数据
                    //得到响应码，必须是200才读取
                    int resquestCode = connection.getResponseCode();
                    if (resquestCode == 200) {
                        // 得到InputStream，并读取成String
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        final String result = baos.toString();

                        baos.close();
                        is.close();

                        //4.在主线程，显示结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
                    //7）断开连接
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    //如果出现异常移除dialog
                    dialog.dismiss();

                }

            }
        }.start();
    }

    /*
     * 使用httpUrlConnection提交post请求
     */

    /**
     * 1.显示ProgressDialog
     * 2.启动分线程
     * 3.在分线程，发送请求，得到响应数据
     * 1）得到path
     * 2）创建url对象
     * 3）打开连接，得到HttpURLConnection
     * 4）设置请求方式，连接超时，读取数据超时
     * 5）连接服务器
     * 6）发请求，得到响应数据
     * 得到输出流，并写请求体：name&age
     * 得到响应码，必须是200才读取
     * 得到InputStream，并读取成String
     * 7)断开连接
     * 4.在主线程，显示结果，移除dialog
     */

    public void testConnectionPost(View v) {
        //1.显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载。。");
        //2.启动分线程
        new Thread(new Runnable() {
            //3.在分线程，发送请求，得到响应数据
            @Override
            public void run() {
                try {
                    //1）得到path
                    String path = et_network_url.getText().toString();
                    // 2）创建url对象
                    URL url = new URL(path);
                    // 3）打开连接，得到HttpURLConnection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // 4）设置请求方式，连接超时，读取数据超时
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    // 5）连接服务器
                    connection.connect();
                    // 6）发请求，得到响应数据
                    // 得到输出流，并写请求体：name & age
                    OutputStream os = connection.getOutputStream();
                    String data = "name=junshuai1&age=211";
                    os.write(data.getBytes("utf-8"));
                    // 得到响应码，必须是200才读取
                    int requseCode = connection.getResponseCode();
                    if (requseCode == 200) {
                        //得到InputStream，并读取成String
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        final String result = baos.toString();


                        baos.close();
                        is.close();

                        //4.在主线程，显示结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
                    os.close();
                    //7）断开连接
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        }).start();
    }

    /*
     * 使用httpClient提交get请求
     */
    public void testClientGet(View v) {
        //1.显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求...");
        //2.启动分线程
        new Thread() {
            //3.在分线程，发送请求，得到响应数据
            public void run() {
                try {
                    //1）得到path，并带上参数name=junshuai&age=21
                    String path = et_network_url.getText().toString() + "?name=junshuai&age=21";
                    //2）创建HttpClient对象

                    //2）设置超时
                    //3）创建Client对象
                    //4）创建请求对象
                    //5）执行请求，得到响应
                    //6）得到响应体文本
                    //4.在主线程，显示数据，移除dialog
                    //7）断开连接
                } catch (Exception e) {
                    e.printStackTrace();
                    //如果出现异常移除dialog
                    dialog.dismiss();

                }

            }
        }.start();
    }

    /*
     * 使用httpClient提交post请求
     */
    public void testClientPost(View v) {

    }

    /*
     * 使用Volley提交get请求
     */
    public void testVolleyGet(View v) {

    }

    /*
     * 使用Volley提交post请求
     */
    public void testVolleyPost(View v) {

    }

}
