# AIDLDemo
Android AIDL demo

Android Developer的官方文档中对AIDL做了很好的概括。当作为客户的一方和要和作为服务器的一方进行通信时，需要指定一些双方都认可的接口， 这样才能顺利地进行通信。而AIDL就是定义这些接口的一种工具。为什么要借助AIDL来定义，而不直接编写接口呢（比如直接通过Java定义一个Interface）？ 这里涉及到进程间通信（IPC）的问题。和大多数系统一样，在Android平台下,各个进程都占有一块自己独有的内存空间，各个进程在通常情况下只能访问自己的独有的内存空间，而不能对别的进程的内存空间进行访问。 进程之间如果要进行通信，就必须先把需要传递的对象分解成操作系统能够理解的基本类型，并根据你的需要封装跨边界的对象。而要完成这些封装工作，需要写的代码量十分地冗长而枯燥。因此Android提供了AIDL来帮助你完成这些工作。

从AIDL的功能来看，它主要的应用场景就是IPC。虽然同一个进程中的client-service也能够通过AIDL定义接口来进行通信，但这并没有发挥AIDL的主要功能。 概括来说：

如果不需要IPC，那就直接实现通过继承Binder类来实现客户端和服务端之间的通信。
如果确实需要IPC，但是无需处理多线程，那么就应该通过Messenger来实现。Messenger保证了消息是串行处理的，其内部其实也是通过AIDL来实现。
在有IPC需求，同时服务端需要并发处理多个请求的时候，使用AIDL才是必要的
在了解了基本的概念和使用场景之后，使用AIDL的基本步骤如下：
1、编写.AIDL文件，定义需要的接口
2、实现定义的接口
3、将接口暴露给客户端调用

注意两个程序中aidl包名必须完全一致，否则会出现 java.lang.SecurityException: Binder invocation to an incorrect interface 错误

直接采用CDSN分享的文章中的代码，但是关于aidl包名及目录细节问题没有写明，因此上传以作参考。
http://blog.csdn.net/fwt336/article/details/52587133