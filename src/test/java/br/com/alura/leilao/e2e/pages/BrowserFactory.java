package br.com.alura.leilao.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowserFactory {

//	http://chromedriver.storage.googleapis.com/index.html
//	https://docs.microsoft.com/en-us/microsoft-edge/webdriver-chromium/?tabs=java

	public WebDriver createWebDriver() {
		String webdriver = System.getProperty("browser", "chrome");//htmlunit chrome edge
		switch (webdriver) {
			case "edge":
				return initEdgeDriver();
			case "chrome":
				return initChromeDriver();
			default:
				return new HtmlUnitDriver();
		}
	}

	private WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		return new ChromeDriver();
	}

	private WebDriver initEdgeDriver() {
		System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
		return new EdgeDriver();
	}
}
