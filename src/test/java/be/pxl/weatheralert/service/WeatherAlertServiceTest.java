package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.Subscriber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherAlertServiceTest {
	@Mock
	private Subscriber subscriber;
	@Mock
	private Alert message;

	@InjectMocks
	private WeatherAlertService weatherAlertService;

	@Test
	public void subscribedClientShouldReceiveMessage() {
		weatherAlertService.addSubscriber(subscriber);
		weatherAlertService.send(message);
		verify(subscriber).receive(message);
	}

	@Test
	public void messageShouldBeSentToAllSubscribedClients() {
		Subscriber subscriber1 = Mockito.mock(Subscriber.class, "subscriber1");
		Subscriber subscriber2 = Mockito.mock(Subscriber.class, "subscriber2");

		weatherAlertService.addSubscriber(subscriber1);
		weatherAlertService.addSubscriber(subscriber2);
		weatherAlertService.send(message);
		verify(subscriber1).receive(message);
		verify(subscriber2).receive(message);
	}

	@Test
	public void notSubscribedClientShouldNotReceiveMessage() {

		weatherAlertService.send(message);
		verify(subscriber, never()).receive(message);
		verifyNoInteractions(subscriber);
	}

	@Test
	public void shouldSendOnlyOneMessageToMultiSubscriber() {

		weatherAlertService.addSubscriber(subscriber);
		weatherAlertService.addSubscriber(subscriber);
		weatherAlertService.send(message);
		verify(subscriber).receive(message);
	}
}
