package be.pxl.weatheralert.service.impl;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.impl.SmsSubscriber;
import be.pxl.weatheralert.service.MessageService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("twilio")
public class TwilioSmsService implements MessageService {

	@Value("${twilio.account_sid}")
	private String accountSid;
	@Value("${twilio.auth_token}")
	private String authToken;
	@Value("${twilio.phone_number}")
	private String phoneNumber;

	@PostConstruct
	public void init() {
		System.out.println(accountSid);
		Twilio.init(accountSid, authToken);
	}

	public void sendMessage(SmsSubscriber subscriber, Alert alert) {
		System.out.println(subscriber.getPhoneNumber());
		System.out.println(accountSid);
		System.out.println(authToken);
		System.out.println(phoneNumber);
		Message.creator(
				new com.twilio.type.PhoneNumber(subscriber.getPhoneNumber()),
				new com.twilio.type.PhoneNumber(phoneNumber),
				alert.getMessage())
				.create();
	}


}
