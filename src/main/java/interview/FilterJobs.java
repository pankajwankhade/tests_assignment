package interview;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FilterJobs {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280,768));
		Page page = context.newPage();
		page.navigate("https://minimals.cc/");
		page.click("//a[text()='Sign in']");
		page.fill("input[name=email]", "demo@minimals.cc");
		page.fill("input[name=password]", "@demo1");
		page.click("button[type=submit]");

		// Navigate to the job list
		page.click("text=Job");
		page.click("text=List");

		// Open the filters panel
		page.click("text=Filters");

		// Select 'On Demand' employment type
		Locator onDemandOption = page.locator("//span[@class='MuiTypography-root MuiTypography-body1 MuiFormControlLabel-label css-30smqk']").nth(2);
		onDemandOption.click();
		// Dismiss the right sidebar
		page.click("//*[@class='MuiBox-root css-hpkoqi']/button[2]");

		// Validate the job filter
		assertThat(page.locator("//*[text()='On demand']").nth(1)).containsText("On demand");

	}

}
