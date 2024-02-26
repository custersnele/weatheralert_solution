package be.pxl.weatheralert.domain.impl;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.service.MessageService;
import be.pxl.weatheralert.domain.Subscriber;

import java.util.Objects;

public class SmsSubscriber implements Subscriber {

	private String nickname;
	private String phoneNumber;

	public SmsSubscriber(String nickname, String phoneNumber) {
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNickname() {
		return nickname;
	}

	@Override
	public void receive(Alert alert, MessageService messageService) {
		messageService.sendMessage(this, alert);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SmsSubscriber that = (SmsSubscriber) o;

		if (!Objects.equals(nickname, that.nickname)) {
			return false;
		}
		return Objects.equals(phoneNumber, that.phoneNumber);
	}

	@Override
	public int hashCode() {
		int result = nickname != null ? nickname.hashCode() : 0;
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SmsSubscriber{" +
				"nickname='" + nickname + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
