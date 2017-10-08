package pl.recruitment.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.recruitment.app.dao.PersonDAO;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Kuba on 2017-10-08.
 */
public class MessageListenerService implements MessageListener {

    private static final Logger _log = LoggerFactory.getLogger(PersonDAO.class);

    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                _log.info("Received textMessage from queue: "
                        + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            _log.info("Received message from queue: " + message);
        }
    }
}
