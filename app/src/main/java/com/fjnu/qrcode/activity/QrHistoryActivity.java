package com.fjnu.qrcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fjnu.qrcode.R;
import com.fjnu.qrcode.adapter.RecordAdapter;
import com.fjnu.qrcode.model.Record;
import com.fjnu.qrcode.server.LocalServer;
import com.fjnu.qrcode.utils.DialogUtil;

import java.util.List;
/**
 * 描述
 *
 * @author Emptying
 * @created 2016/9/12
 */
public class QrHistoryActivity extends AppCompatActivity {

    private LocalServer localServer;
    private List<Record> recordList;
    private ListView lvRecord;
    private RecordAdapter recordAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_history);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        lvRecord = (ListView) findViewById(R.id.lv_history);

    }

    private void initData() {
        localServer = LocalServer.getInstance(QrHistoryActivity.this);
        recordList = localServer.getAllRecord();

        recordAdapter = new RecordAdapter(QrHistoryActivity.this, recordList);
        lvRecord.setAdapter(recordAdapter);
    }

    private void initEvent() {
        lvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stringContent = recordAdapter.getContent(i);
                DialogUtil.getQrDialog(QrHistoryActivity.this, "全能扫码", stringContent, null).show();
            }
        });
    }
}
