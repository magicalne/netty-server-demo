package magicalne.nettyserverdemo;

import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.util.Utf8;

/**
 * Author: zehui.lv@dianrong on 6/14/17.
 */
public class MailImpl implements Mail {
    @Override
    public Utf8 send(Message message) {
        System.out.println("Sending message");
        return new Utf8("Sending message to " + message.getTo().toString()
                                + " from " + message.getFrom().toString()
                                + " with body " + message.getBody().toString());
    }
}
