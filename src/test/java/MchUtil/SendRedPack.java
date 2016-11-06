package MchUtil;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

public class SendRedPack {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, FileNotFoundException {
		CharBuffer buffer = CharBuffer.allocate(11);
		buffer.put("aAa");
		buffer.flip();
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		System.out.println(buffer.get(0));
		System.out.println(buffer.get());
		RandomAccessFile file = new RandomAccessFile("", "r");
		FileChannel channel = file.getChannel();
		SelectionKey key = null;
		
	}
	
}
