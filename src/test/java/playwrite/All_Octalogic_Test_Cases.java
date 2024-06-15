package playwrite;

import com.microsoft.playwright.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
public class All_Octalogic_Test_Cases {
	private static Playwright playwright;
	private Browser browser;
	private Page page;

	@BeforeAll
	static void setupAll() {
		playwright = Playwright.create();
	}

	@AfterAll
	static void teardownAll() {
		playwright.close();
	}

	@BeforeEach
	void setUp() {
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280,768));
		page = context.newPage();
		// Navigate to the login page
		page.navigate("https://minimals.cc/");
		page.click("//a[text()='Sign in']");
		// Perform login
		page.fill("input[name=email]", "demo@minimals.cc");
		page.fill("input[name=password]", "@demo1");
		page.click("button[type=submit]");
	}

	@AfterEach
	void tearDown() {
		page.close();
		browser.close();
	}

	@Test
	@Order(1)
	void updateBillingInformation() {
		// Navigate to the billing section
		page.click("text=User");
		page.click("text=Account");
		page.click("text=Billing");

		// Update billing information
		page.click("text=Jayvion Simon");
		page.click("//*[@class='MuiStack-root css-hp68mp']//h6[text()='Deja Brady']");
		page.click("text=**** **** **** 5678");
		page.click(".MuiPaper-root.MuiPaper-outlined.MuiPaper-rounded.css-yvb32y:has-text('**** **** **** 1234')");

		// Validate the changes
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


