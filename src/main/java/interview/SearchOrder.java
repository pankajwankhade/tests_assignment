package interview;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchOrder {

	public static void main(String [] args) {    
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280,768));
		Page page = context.newPage();
		page.navigate("https://minimals.cc/");
		page.click("//a[text()='Sign in']");
		page.fill("input[name=email]", "demo@minimals.cc");
		page.fill("input[name=password]", "@demo1");
		page.click("button[type=submit]");
		
		
		// Navigate to the order list
				page.click("text=Order");
				page.click("text=List");

				// Perform a search
				page.fill("//input[@placeholder='Search customer or order number...']","cor");
                
				// Validate the search results
				Locator resultRow = page.locator("text=Cortez Herring");
				assertThat(resultRow).isVisible();
				assertThat(page.locator("//tbody/tr[@class='MuiTableRow-root MuiTableRow-hover css-1goe4p7']")).hasCount(1);
		

	}
}
