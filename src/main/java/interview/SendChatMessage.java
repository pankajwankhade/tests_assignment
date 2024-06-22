package interview;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SendChatMessage {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1345,700));
		Page page = context.newPage();
		page.navigate("https://minimals.cc/");
		page.click("//a[text()='Sign in']");
		page.fill("input[name=email]", "demo@minimals.cc");
		page.fill("input[name=password]", "@demo1");
		page.click("button[type=submit]");

		// Navigate to the chat
		page.click("text=Chat");

		// Select the chat with 'Deja Brady'
		page.click("//span[text()='Deja Brady']");
  
		// Send the message
		page.fill("input[name=chat-message]", "Hello, how are you?");
		page.keyboard().press("Enter");

		// Validate the message was sent
		assertThat(page.locator("//div[text()='Hello, how are you?']")).isVisible();

	}

}
