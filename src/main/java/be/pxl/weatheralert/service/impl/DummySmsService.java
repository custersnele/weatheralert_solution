package be.pxl.weatheralert.service.impl;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.Subscriber;
import be.pxl.weatheralert.service.MessageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dummy")
public class DummySmsService implements MessageService {

	@Override
	public void sendMessage(Subscriber subscriber, Alert alert) {
		System.out.println("Sending message [" + alert.getMessage() + "] to [" + subscriber.getPhoneNumber() + "]");
	}
}
