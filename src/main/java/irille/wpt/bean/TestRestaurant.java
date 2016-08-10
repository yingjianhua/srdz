package irille.wpt.bean;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRestaurant {
	
	public static void main(String[] args) {
		new TestRestaurant().test();
	}
	public void test() {
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml", "spring-bean.xml");
		SessionFactory sessionFactory = (SessionFactory)bf.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Iterator<Object> i = session.createQuery("select w from Restaurant w").iterate();
		while(i.hasNext()) {
			Object o = i.next();
			System.out.println(o);
		}
		List list = session.getNamedQuery("Restaurant.findAll").list();
		System.out.println("list.size():"+list.size());
		for(Object line:list) {
			System.out.println(line);
		}
		
		session.close();
	}
}
