package interview;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DeleteFiles {

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

		// Navigate to the file manager
		page.click("text=File Manager");

		// Select all items
		page.click("input[type=checkbox]");

		// Initiate delete action
		page.click("button[aria-label=Delete]");
		page.click("//button[text()='Delete']");

		// Validate all items are deleted
		assertThat(page.locator("text=No data")).isVisible();

	}

}
