package me.rain.android.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by CBS on 2017/1/13.
 */

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {

        @Override
        public HelloMsg sayHello() throws RemoteException {
            return new HelloMsg("msg from service at thread " + Thread.currentThread().toString() + "\n"
                    + "thread id is " + Thread.currentThread().getId() + "\n"
                    + "main thread id is " + getMainLooper().getThread().getId(), Process.myPid());
        }
    };
}
