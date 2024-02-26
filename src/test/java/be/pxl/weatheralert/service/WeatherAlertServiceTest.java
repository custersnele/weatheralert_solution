package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.Subscriber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WeatherAlertServiceTest {
	@Mock
	private Subscriber subscriber;
	@Mock
	private MessageService messageService;
	@Mock
	private Alert message;
	@InjectMocks
	private WeatherAlertService weatherAlertService;

	@Test
	public void subscribedClientShouldReceiveMessage() {
		weatherAlertService.addSubscriber(subscriber);
		weatherAlertService.send(message);
		verify(subscriber).receive(message, messageService);
	}

	@Test
	public void messageShouldBeSentToAllSubscribedClients() {
		Subscriber subscriber1 = Mockito.mock(Subscriber.class, "subscriber1");
		Subscriber subscriber2 = Mockito.mock(Subscriber.class, "subscriber2");

		weatherAlertService.addSubscriber(subscriber1);
		weatherAlertService.addSubscriber(subscriber2);
		weatherAlertService.send(message);
		verify(subscriber1).receive(message, messageService);
		verify(subscriber2).receive(message, messageService);
	}

	@Test
	public void notSubscribedClientShouldNotReceiveMessage() {
		Subscriber subscriber1 = Mockito.mock(Subscriber.class, "subscriber1");

		weatherAlertService.send(message);
		verify(subscriber1, never()).receive(message, messageService);
	}

	@Test
	public void shouldSendOnlyOneMessageToMultiSubscriber() {
		Subscriber subscriber1 = Mockito.mock(Subscriber.class, "subscriber1");

		weatherAlertService.addSubscriber(subscriber1);
		weatherAlertService.addSubscriber(subscriber1);
		weatherAlertService.send(message);
		verify(subscriber1).receive(message, messageService);
	}
}
