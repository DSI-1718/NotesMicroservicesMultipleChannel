package cat.tecnocampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ReverseproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseproxyApplication.class, args);
	}
}
