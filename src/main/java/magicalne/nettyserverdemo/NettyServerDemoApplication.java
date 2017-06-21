package magicalne.nettyserverdemo;

import example.proto.Mail;
import org.apache.avro.ipc.Ipc;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class NettyServerDemoApplication {

	private static void start() throws URISyntaxException, IOException {
		Executor bossExecutor = Executors.newCachedThreadPool();
		ExecutorService ioWorkerExecutor = Executors.newCachedThreadPool();
		OrderedMemoryAwareThreadPoolExecutor orderedMemoryAwareThreadPoolExecutor =
				new OrderedMemoryAwareThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 0, 0);
		ExecutionHandler executionHandler = new ExecutionHandler(orderedMemoryAwareThreadPoolExecutor);
		final Server server = new NettyServer(new SpecificResponder(Mail.class, new MailImpl()),
											  new InetSocketAddress(65111),
											  new NioServerSocketChannelFactory(bossExecutor, ioWorkerExecutor),
											  executionHandler);
		server.start();
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(NettyServerDemoApplication.class, args);
		start();
	}
}
