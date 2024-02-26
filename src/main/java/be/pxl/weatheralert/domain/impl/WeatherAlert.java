package be.pxl.weatheralert.domain.impl;

import be.pxl.weatheralert.domain.Alert;

public class WeatherAlert implements Alert {
	private String title;
	private String description;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String getMessage() {
		return title + " - " + description;
	}

	@Override
	public String toString() {
		return "WeatherAlert{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
