package be.pxl.weatheralert.service.impl;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.impl.SmsSubscriber;
import be.pxl.weatheralert.service.MessageService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dummy")
public class DummySmsService implements MessageService {


	public void sendMessage(SmsSubscriber subscriber, Alert alert) {
		System.out.println("Sending message [" + alert.getMessage() + "] to [" + subscriber.getPhoneNumber() + "]");
	}


}
