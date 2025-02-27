package be.pxl.weatheralert.domain;

import be.pxl.weatheralert.service.MessageService;

public interface Subscriber {
	void receive(Alert message);

	String getPhoneNumber();

}
