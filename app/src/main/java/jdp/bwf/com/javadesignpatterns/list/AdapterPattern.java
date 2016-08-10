package jdp.bwf.com.javadesignpatterns.list;

/**适配器模式(Adapter Pattern)
 * 将一个类的接口转换成客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作。
 * 目标接口（Target）：客户所期待的接口。目标可以是具体的或抽象的类，也可以是接口。
 * 需要适配的类（Adaptee）：需要适配的类或适配者类。
 * 适配器（Adapter）：通过包装一个需要适配的对象，把原接口转换成目标接口。
 * 适配器模式包含：类适配器模式和对象适配器模式
 * 类(Class)适配器模式：类的适配器模式把适配的类的API转换成为目标类的API
 * 对象(Instance)适配器模式：与类的适配器模式一样，对象的适配器模式把被适配的类的API转换成为目标类的API，
 * 与类的适配器模式不同的是，对象的适配器模式不是使用继承关系连接到Adaptee类，而是使用委派关系连接到Adaptee类。
 */
public class AdapterPattern {
    public interface Target {
        //Adaptee的方法
        public void sampleOperation();
        //Adaptee没有的方法
        public void sampleOperationEdited();
    }

    public class Adaptee {
        public void  sampleOperation(){};
    }

    /**
     * 类的适配器
     */
    public class AdapterClass extends Adaptee implements Target {
        /**
         * 适配器中补充的方法
         */
        @Override
        public void sampleOperationEdited() {
            //Functions
        }
    }

    /**
     * 对象的适配器
     */
    public  class AdapterInstance{
        private Adaptee adaptee;
        public AdapterInstance(Adaptee adaptee){
            if (adaptee!=null){
                this.adaptee = new Adaptee();
            }else{
                this.adaptee = adaptee;
            }
        }
        public void sampleOperation(){
            this.adaptee.sampleOperation();
        }
        public void sampleOperationEdited(){
            //Functions
        }
    }
}
