package org.Exercise1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exercise1 {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Greeting greeting = ( Greeting ) context.getBean("Greeting");
        greeting.printGreeting();

    }

}
