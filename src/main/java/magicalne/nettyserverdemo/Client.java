package magicalne.nettyserverdemo;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.ipc.Ipc;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.Transceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.util.Utf8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Author: zehui.lv@dianrong on 6/14/17.
 */
public class Client {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final NettyTransceiver client = new NettyTransceiver(new InetSocketAddress(65111));
        // client code - attach to the server and send a message
        Mail proxy = SpecificRequestor.getClient(Mail.class, client);
        System.out.println("Client built, got proxy");

        // fill in the Message record and send it
        Message message = new Message();
        message.setTo(new Utf8("asdf"));
        message.setFrom(new Utf8("from"));
        message.setBody(new Utf8("hahahaha"));
        System.out.println("Calling proxy.send with message:  " + message.toString());
        System.out.println("Result: " + proxy.send(message));
    }
}
