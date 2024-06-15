package interview;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class UpdateBillingInformation {

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

		page.click("text=User");
		page.click("text=Account");
		page.click("text=Billing");

		// Update billing information
		page.click("text=Jayvion Simon");
		page.click("//*[@class='MuiStack-root css-hp68mp']//h6[text()='Deja Brady']");
		page.click("text=**** **** **** 5678");
		page.click(".MuiPaper-root.MuiPaper-outlined.MuiPaper-rounded.css-yvb32y:has-text('**** **** **** 1234')");
		//	page.click("//*[@class='MuiPaper-root MuiPaper-outlined MuiPaper-rounded css-yvb32y']//h6[text()='**** **** **** 1234']");

		boolean billingName = page.locator("text=billingName").isVisible();
		boolean  name = page.locator("//*[@class='MuiStack-root css-hp68mp']//h6[text()='Deja Brady']").isVisible();
		assertEquals(billingName,name);

		boolean paymentMethod=  page.locator("//div[text()='Payment method']").isVisible();
		//System.out.println(paymentMethod);
		boolean card= page.locator("//button[text()='**** **** **** 1234']").isVisible();
		//System.out.println(card);
		assertEquals(paymentMethod,card);
	}

}
