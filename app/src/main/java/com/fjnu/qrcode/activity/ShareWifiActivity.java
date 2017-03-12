package com.fjnu.qrcode.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fjnu.qrcode.R;
import com.fjnu.qrcode.adapter.WifiMsgAdapter;
import com.fjnu.qrcode.utils.DialogUtil;
import com.fjnu.qrcode.utils.StrUtil;
import com.fjnu.qrcode.server.WifiManage;
/**
 * 描述
 *
 * @author Emptying
 * @created 2016/9/11
 */
public class ShareWifiActivity extends AppCompatActivity {

    /**
     * 获取当前手机中连接过的wifi名称 管理
     */
    private WifiManage wifiManage;
    private ListView lvWifiMsg;
    private WifiMsgAdapter wifiMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_wifi);

        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        lvWifiMsg = (ListView) findViewById(R.id.lv_wifi_msg);
    }

    /**
     * 初始化数据
     * 初始化适配器等
     */
    private void initData() {
        wifiManage = new WifiManage(this);

        wifiMsgAdapter = new WifiMsgAdapter(wifiManage.read(), this);
        lvWifiMsg.setAdapter(wifiMsgAdapter);
    }

    private void initEvent() {

        lvWifiMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stringContent = wifiMsgAdapter.getString(i);
                if (stringContent == null) {
                    //解决Toast问题
                    StrUtil.showToast(ShareWifiActivity.this, "此wifi不需要密码即可连接", Toast.LENGTH_LONG);
                } else {
                    Dialog dialog = DialogUtil.getQrDialog(ShareWifiActivity.this, "扫一扫连接此wifi", stringContent, null);
                    dialog.show();
                }
            }
        });
    }

    //    public void InitWifi(){
    //        List<WifiMsg> wifiInfos = wifiManage.read();
    //    }
}
