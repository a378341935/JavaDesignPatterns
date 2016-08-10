package jdp.bwf.com.javadesignpatterns.list;

import android.os.Parcelable;
import android.widget.LinearLayout;

import java.io.Serializable;

/**单例模式（Singleton）
 * 单例对象（Singleton）是一种常用的设计模式。在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在。这样的模式有几个好处：
 * 1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 * 2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 * 3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。
 * （比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。
 */
public class Singleton {
    //持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
    private static Singleton instance = null;
    //私有构造方法，防止被实例化
    private Singleton(){};
    //静态工程方法，创建实例
    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    //序列化时需要添加的方法
    private Singleton readResolve(){
        return instance;
    }
    /**
     * 以上的模式在多线程时显然不适用，于是在多线程中需要同步处理
     */

    public static synchronized Singleton getInstanceSyn() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    /**
     * 但是，synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，
     * 因为每次调用getInstance()，都要对对象上锁，事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了，所以，这个地方需要改进。
     */

    public static Singleton getInstanceSynImproved() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    /**
     * 在Java指令中创建对象和赋值操作是分开进行的，也就是说instance = new Singleton();
     * 语句是分两步执行的。但是JVM并不保证这两个操作的先后顺序，也就是说有可能JVM会为新的Singleton实例分配空间，
     * 然后直接赋值给instance成员，然后再去初始化这个Singleton实例。
     */

    //采用内部类的方式来维护单例
    private static class SingletonFactory {
        private static Singleton instance = new Singleton();
    }
    //获取实例
    public static Singleton getInstanceStatic(){
        return SingletonFactory.instance;
    }
}
