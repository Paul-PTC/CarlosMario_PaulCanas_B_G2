package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PaulCanasCarlosMario2BApplication.class);
	}

}
