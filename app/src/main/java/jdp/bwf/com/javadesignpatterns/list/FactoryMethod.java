package jdp.bwf.com.javadesignpatterns.list;

/**工厂方法模式（Factory Method）
 *
 */
public class FactoryMethod {

    /**
     * 普通工厂模式
     */
    public interface SenderNormal{
        public void send();
    }
    public class MailSenderNormal implements SenderNormal{
        @Override
        public void send() {
            System.out.print("this is the normal mail sender");
        }
    }
    public class SmsSenderNormal implements  SenderNormal{
        @Override
        public void send() {
            System.out.print("this is the normal sms sender");
        }
    }
    public class sendFactoryNormal{
        public SenderNormal produce(String type){
            if (type.equals("mail")){
                return  new MailSenderNormal();
            }else if (type.equals("sms")){
                return  new SmsSenderNormal();
            }else{
                System.out.print("there is no such type existing");
                return null;
            }
        }
    }
    /**
     * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，
     * 则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象。
     */
    public class SendFactoryMultiple{
        public SenderNormal produceMail(){
            return new MailSenderNormal();
        }

        public SenderNormal produceSms(){
            return new SmsSenderNormal();
        }
    }

    /**静态工厂方法模式
     * 将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
     * 这里的类必须写成static是因为内部类的方法是静态方法，如果此类不是内部类，则无此必要
     */
    public static class SendFactoryStatic{
        public static SenderNormal produceMail(){
            //这里不能使用this是因为静态内部类不能访问外部类的非静态属性,this属于非静态属性
            return new FactoryMethod().new MailSenderNormal();
        }

        public static SenderNormal produceSms(){
            return new FactoryMethod().new SmsSenderNormal();
        }
    }
    /**
     * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
     * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，
     * 不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
     */

    /**
     * 抽象工厂模式(Abstract Factory)
     * 因为上面的方式在需要添加新的被生产的产品的时候，必须要对工厂类（负责生产的）进行修改
     * 违背了闭合原则，所以可以考虑使用抽象工厂，需要添加新产品的时候只需要添加新的类来拓展就行了
     */
    public interface SenderAbstract{
        public void send();
    }

    public class MailSenderAbstract implements  SenderAbstract{
        @Override
        public void send() {
            System.out.print("this is the abstract mail sender");
        }
    }

    public  class SmsSenderAbstract implements SenderAbstract{
        @Override
        public void send() {
            System.out.print("this is the abstract sms sender");
        }
    }

    public interface AbstractFactory{
        public SenderAbstract produce();
    }

    public class MailFactory implements AbstractFactory{
        @Override
        public SenderAbstract produce() {
            return new MailSenderAbstract();
        }
    }

    public class SmsFactory implements AbstractFactory{
        @Override
        public SenderAbstract produce() {
            return new SmsSenderAbstract();
        }
    }
}
