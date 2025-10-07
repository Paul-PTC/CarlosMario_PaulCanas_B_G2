package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaulCanasCarlosMario2BApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(PaulCanasCarlosMario2BApplication.class, args);
	}

}
