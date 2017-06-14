package magicalne.nettyserverdemo;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.ipc.Callback;
import org.apache.avro.util.Utf8;

import java.io.IOException;

/**
 * Author: zehui.lv@dianrong on 6/14/17.
 */
public class MailImpl implements Mail.Callback {
    @Override
    public Utf8 send(Message message) {
        System.out.println("Sending message");
        return new Utf8("Sending message to " + message.getTo().toString()
                                + " from " + message.getFrom().toString()
                                + " with body " + message.getBody().toString());
    }

    @Override
    public void send(Message message, org.apache.avro.ipc.Callback<CharSequence> callback)
            throws IOException {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".....");
        callback.handleResult(new Utf8("Sending message to " + message.getTo().toString()
                + " from " + message.getFrom().toString()
                + " with body " + message.getBody().toString()
                + " callback######"));
    }
}
