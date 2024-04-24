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

		ServletContext servletContext = request.getServletContext();
		String realPath = servletContext.getRealPath("/");
		System.out.println("realPath : "+realPath.substring(0, realPath.indexOf("target")));

		String currentDirectory = System.getProperty("user.home");
		// 파일을 생성할 상대 경로를 지정합니다.
		String folderPath = "src/main/resources/img";

		System.out.println("path : "+currentDirectory+"|"+folderPath);

		String rootPath = request.getSession().getServletContext().getContext("/resources").getRealPath("") ;
		System.out.println("Current directory: " + rootPath);

		return "home";
	}
}
