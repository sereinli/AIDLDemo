// IRemoteService.aidl
package me.rain.android.aidl;

// Declare any non-default types here with import statements
import me.rain.android.aidl.HelloMsg;

interface IRemoteService {
    HelloMsg sayHello();
}
