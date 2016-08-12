package irille.wpt.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import irille.wpt.service.ComboService;

public class TestRestaurant {
	
	public static void main(String[] args) {
		new TestRestaurant().test();
	}
	public void test() {
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml", "spring-bean.xml");
		ComboService service = (ComboService)bf.getBean("comboService");
		System.out.println(service);
		Combo combo = service.get(30);
		System.out.println(combo);
		System.out.println(combo.getRestaurant().getAccount());
		System.out.println(combo.getRestaurant());
	}
}
