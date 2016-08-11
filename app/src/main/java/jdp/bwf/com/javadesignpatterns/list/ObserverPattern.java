package jdp.bwf.com.javadesignpatterns.list;

import java.util.ArrayList;
import java.util.List;

/**观察者（Observer）
 * 观察者模式是对象的行为模式，又叫发布-订阅(Publish/Subscribe)模式、
 * 模型-视图(Model/View)模式、源-监听器(Source/Listener)模式或从属者(Dependents)模式
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
 * 角色：
 * 抽象主题(Subject)角色：抽象主题角色把所有对观察者对象的引用保存在一个聚集（比如ArrayList对象）里，每个主题都可以有任何数量的观察者。
 *                        抽象主题提供一个接口，可以增加和删除观察者对象，抽象主题角色又叫做抽象被观察者(Observable)角色。
 * 具体主题(ConcreteSubject)角色：将有关状态存入具体观察者对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。
 *                                具体主题角色又叫做具体被观察者(Concrete Observable)角色。
 * 抽象观察者(Observer)角色：为所有的具体观察者定义一个接口，在得到主题的通知时更新自己，这个接口叫做更新接口。
 * 具体观察者(ConcreteObserver)角色：存储与主题的状态自恰的状态。具体观察者角色实现抽象观察者角色所要求的更新接口，
 *                                   以便使本身的状态与主题的状态 像协调。如果需要，具体观察者角色可以保持一个指向具体主题对象的引用。
 */
public class ObserverPattern {

    public abstract class subject {
        //抽象主题角色类
        /**
         * 观察者对象的List
         */
        private List<Observer> list = new ArrayList<Observer>();
        /**
         * 添加/注册一个观察者
         */
        public void attach(Observer observer){
            list.add(observer);
            System.out.print("One Observer Attached");
        }
        /**
         * 删除/撤销一个观察者
         */
        public void detach(Observer observer){
            list.remove(observer);
            System.out.print("One Observer Detached");
        }
        /**
         * 通知观察者
         */
        public void notifyAllObservers(String newState){
            for (Observer observer:list) {
                observer.update(newState);
            }
        }
    }

    public class ConcreteSubject extends subject {
        private String state;
        public String getState(){
            return  state;
        }
        public void change(String newState){
            state = newState;
            System.out.print("Current State:"+ newState);
            this.notifyAllObservers(newState);
        }
    }

    public interface Observer {
        /**
         * 更新的接口
         * @param state = 状态
         */
        public void update(String state);
    }

    public class ConcreteObserver implements Observer{

        private String observerState;

        @Override
        public void update(String state) {
            this.observerState = state;
            System.out.print("Current Observer State:"+ state);
        }
    }

    //MoreDetail  = http://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
}
