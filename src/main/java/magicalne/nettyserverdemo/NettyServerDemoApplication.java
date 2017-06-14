package magicalne.nettyserverdemo;

import example.proto.Mail;
import org.apache.avro.ipc.Ipc;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class NettyServerDemoApplication {

	static void start() throws URISyntaxException, IOException {
		final Server server = new NettyServer(new SpecificResponder(Mail.class, new MailImpl()),
											  new InetSocketAddress(65111));
		server.start();
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(NettyServerDemoApplication.class, args);
		start();
	}
}
