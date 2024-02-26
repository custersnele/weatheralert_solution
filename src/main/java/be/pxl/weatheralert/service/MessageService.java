package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.impl.SmsSubscriber;

public interface MessageService {
	void sendMessage(SmsSubscriber subscriber, Alert alert);
}
