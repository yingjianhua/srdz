package MchUtil;

import annotation.Sendable;

public class SendRedPack {
	@Sendable
	public String name;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		System.out.println(SendRedPack.class.getField("name").getName());
		System.out.println(SendRedPack.class.getField("name").getAnnotation(Sendable.class));
	}
}
