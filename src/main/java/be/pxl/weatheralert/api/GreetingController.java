package be.pxl.weatheralert.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greetings")
public class GreetingController {

	@GetMapping
	public String getMappingWithCookie(HttpServletResponse response) {
		response.addCookie(new Cookie("greeting", "test"));
		return "Hello world";
	}
}
