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
    public class sendFactory{
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
}
