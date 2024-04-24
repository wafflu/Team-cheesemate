package team.cheese.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws IOException {
//		ServletContext servletContext = request.getServletContext();
//		String relativePath = "/resources/img";
//		String realPath = servletContext.getRealPath(relativePath);

		Resource resource = resourceLoader.getResource("file:/resources/**");

		System.out.println("resource : "+resource);

		String resourcePath = "classpath:/";
		String absolutePath = resourceLoader.getResource(resourcePath).getURL().getPath();

		System.out.println(absolutePath);
		return "home";
	}
}
