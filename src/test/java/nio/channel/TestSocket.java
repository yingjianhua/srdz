package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestSocket {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
		ByteBuffer buffer = ByteBuffer.allocate(10);
		//buffer.clear();
		System.out.println(socketChannel.getClass().getName());
		while(socketChannel.read(buffer) != -1) {
			buffer.flip();
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			System.out.println(new String(data));
			buffer.clear();
		}
	}
}
