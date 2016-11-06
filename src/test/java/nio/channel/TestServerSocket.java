package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServerSocket {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress("127.0.0.1", 8080));
		Integer index = 1;
		String pre = "你第";
		while(true) {
			SocketChannel c = channel.accept();
			System.out.println("接收到一個socket鏈接");
			c.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocate(10);
			buffer.clear();
			byte[] data = (pre+index++).getBytes(); 
			buffer.put(data);
			buffer.flip();
			c.write(buffer);
		}
	}
}
