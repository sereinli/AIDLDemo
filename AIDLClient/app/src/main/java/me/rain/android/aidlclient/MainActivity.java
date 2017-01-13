package me.rain.android.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import me.rain.android.aidl.IRemoteService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView;
    private Button mBtnShowPid;
    private Button mBtnSayHello;
    private Button mBtnBindService;
    private Button mBtnUnbindService;

    private IRemoteService mRemoteService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
            Toast.makeText(MainActivity.this, "Bind success!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteService = null;
            Toast.makeText(MainActivity.this, "Unbind success!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mTextView = (TextView)findViewById(R.id.tv_text);

        mBtnShowPid = (Button)findViewById(R.id.btn_show_pid);
        mBtnShowPid.setOnClickListener(this);

        mBtnSayHello = (Button)findViewById(R.id.btn_say_hello);
        mBtnSayHello.setOnClickListener(this);

        mBtnBindService = (Button)findViewById(R.id.btn_bind_service);
        mBtnBindService.setOnClickListener(this);

        mBtnUnbindService = (Button)findViewById(R.id.btn_unbind_service);
        mBtnUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_show_pid) {
            if(mRemoteService != null) {
                try {
                    mTextView.setText(String.valueOf(mRemoteService.sayHello().getPid()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(MainActivity.this, "No bind service!", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId() == R.id.btn_say_hello) {
            try {
                mTextView.setText(String.valueOf(mRemoteService.sayHello().getMsg()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if(view.getId() == R.id.btn_bind_service) {
            if(mConnection != null) {
                Toast.makeText(MainActivity.this, "Service has bind!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent("me.rain.android.learning.remoteaidl");
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        }else if(view.getId() == R.id.btn_unbind_service) {
            if(mConnection != null) {
                unbindService(mConnection);
            }else {
                Toast.makeText(MainActivity.this, "No bind service!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(mConnection != null) {
            unbindService(mConnection);
        }

        super.onDestroy();
    }
}
