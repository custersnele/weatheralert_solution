package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.Subscriber;
import be.pxl.weatheralert.domain.impl.SmsSubscriber;

public interface MessageService {
	void sendMessage(Subscriber subscriber, Alert alert);
}
