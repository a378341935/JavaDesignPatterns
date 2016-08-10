package jdp.bwf.com.javadesignpatterns.list;

import java.util.ArrayList;
import java.util.List;

/**建造者模式（Builder）
 * 工厂类模式提供的是创建单个类的模式，而建造者模式则是将各种产品集中起来进行管理，
 * 用来创建复合对象，所谓复合对象就是指某个类具有不同的属性
 *
 */
public class Builder {

    public interface Sender{
        public void send();
    }

    public class MailSender implements Sender{
        @Override
        public void send() {
            System.out.print("this is the normal mail sender");
        }
    }
    public class SmsSender implements  Sender{
        @Override
        public void send() {
            System.out.print("this is the normal sms sender");
        }
    }

    private List<Sender> list = new ArrayList<Sender>();

    /**
     * 建造者模式，用List进行管理，List中可以存在不同对象
     */
    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }
}
