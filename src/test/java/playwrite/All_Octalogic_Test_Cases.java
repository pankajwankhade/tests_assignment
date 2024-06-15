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
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1330,700));
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

	@Test
	@Order(2)
	void searchOrder() {
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
	@Test
	@Order(3)
	void filterJobs() {

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
	@Test
	@Order(4)
	void sendChatMessage() {
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

	@Test
	@Order(5)
	void deleteFiles() {

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


