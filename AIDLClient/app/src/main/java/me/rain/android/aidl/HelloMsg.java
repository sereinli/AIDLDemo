package me.rain.android.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CBS on 2017/1/12.
 */

public class HelloMsg implements Parcelable {
    private String msg;
    private int pid;

    public HelloMsg(String msg, int pid) {
        this.msg = msg;
        this.pid = pid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.msg);
        parcel.writeInt(this.pid);
    }

    protected HelloMsg(Parcel parcel) {
        this.msg = parcel.readString();
        this.pid = parcel.readInt();
    }

    public static final Parcelable.Creator<HelloMsg> CREATOR = new Parcelable.Creator<HelloMsg>() {

        @Override
        public HelloMsg createFromParcel(Parcel parcel) {
            return new HelloMsg(parcel);
        }

        @Override
        public HelloMsg[] newArray(int i) {
            return new HelloMsg[i];
        }
    };
}
