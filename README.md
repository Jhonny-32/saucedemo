# SauceDemo Test Automation

Selenium WebDriver + Cucumber + Java automation framework for [SauceDemo](https://www.saucedemo.com/), covering login, product browsing, cart management, and end-to-end checkout.

## Prerequisites

| Tool | Version |
|------|---------|
| Java JDK | 11 or higher |
| Gradle | 8.x (wrapper included) |
| Google Chrome | Latest stable |
| ChromeDriver | Matching your Chrome version |

> The project includes a ChromeDriver binary at `src/test/resources/configuration/chromedriver.exe` (Windows). If your Chrome version differs, replace it with the matching driver from [chromedriver.chromium.org](https://chromedriver.chromium.org/downloads).

## Clone the Repository

```bash
git clone https://github.com/Jhonny-32/saucedemo.git
cd saucedemo
```

## Project Structure

```
saucedemo/
├── src/test/
│   ├── java/com/com/saucedemo/
│   │   ├── function/        # Hooks (browser setup/teardown) and TextContext
│   │   ├── page/            # Page Object classes (BasePage, LoginPage, ProductsPage, ...)
│   │   ├── runner/          # JUnit Platform Suite runner
│   │   ├── steps/           # Cucumber step definitions
│   │   └── utilities/       # ExtentReports, screenshot, highlight helpers
│   └── resources/
│       ├── configuration/   # ChromeDriver binary
│       ├── features/        # Gherkin feature files (TC-001 to TC-005)
│       └── cucumber.properties
├── reports/                 # Generated HTML report and screenshots (git-ignored)
└── build.gradle
```

## Running the Tests

### Run all tests

```bash
./gradlew test
```

On Windows:

```bash
gradlew.bat test
```

### Run a specific tag

Edit the tag filter in `src/test/java/com/com/saucedemo/runner/RunCucumberTest.java`:

```java
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@Login")
```

Available tags:

| Tag | Test Case |
|-----|-----------|
| `@Login` | TC-001 and TC-002 |
| `@Positive` | TC-001 |
| `@Negative` | TC-002 |
| `@Products` | TC-003 |
| `@Cart` | TC-004 |
| `@Checkout` | TC-005 |
| `@E2E` | TC-005 |

## Test Report

After execution, open the HTML report:

```
reports/index.html
```

Each step includes a screenshot with element highlight (red border). The report uses a dark theme powered by ExtentReports.

---

## Test Cases

### TC-001 — Successful Login with Valid Credentials

**Preconditions:**
- Browser is open and navigated to `https://www.saucedemo.com/`
- Valid credentials available: `standard_user` / `secret_sauce`

| # | Step |
|---|------|
| 1 | Enter username `standard_user` in the username field |
| 2 | Enter password `secret_sauce` in the password field |
| 3 | Click the **Login** button |
| 4 | Wait for the page to load |

**Expected Result:**
- User is successfully logged in
- The Products page is displayed with the inventory list and product cards
- Shopping cart icon appears in the top right corner

---

### TC-002 — Login Failure with Invalid Credentials

**Preconditions:**
- Browser is open and navigated to `https://www.saucedemo.com/`
- Invalid credentials will be used

| # | Step |
|---|------|
| 1 | Enter username `invalid_user` in the username field |
| 2 | Enter password `wrong_password` in the password field |
| 3 | Click the **Login** button |
| 4 | Wait for the response |

**Expected Result:**
- Login fails
- Error message displayed: *"Username and password do not match any user in this service"*
- User remains on the login page

---

### TC-003 — Filter Products by Price and Select an Item

**Preconditions:**
- User is successfully logged in
- Products page is displayed with at least 6 products visible

| # | Step |
|---|------|
| 1 | Click on the **Sort** dropdown on the Products page |
| 2 | Select **Price (low to high)** |
| 3 | Wait for products to be reordered |
| 4 | Click on the first product from the filtered list |
| 5 | Verify the product detail page is displayed |

**Expected Result:**
- Products are sorted by price from lowest to highest
- Clicking a product opens its detail page with: image, name, price, description, and **Add to Cart** button

---

### TC-004 — Add Product to Shopping Cart

**Preconditions:**
- User is logged in and on the Products page
- Shopping cart is empty

| # | Step |
|---|------|
| 1 | Click **Add to Cart** on any product card |
| 2 | Verify the button changes to **Remove** |
| 3 | Click the shopping cart icon |
| 4 | Verify the cart page shows the added product |

**Expected Result:**
- **Add to Cart** button changes to **Remove**
- Cart badge shows `1` next to the cart icon
- Cart page displays the product with name, price, and quantity
- Subtotal is calculated correctly

---

### TC-005 — Complete End-to-End Checkout Process

**Preconditions:**
- User is logged in
- At least one product is added to the cart
- User is on the shopping cart page

| # | Step |
|---|------|
| 1 | Click the **Checkout** button |
| 2 | Enter first name `John` |
| 3 | Enter last name `Doe` |
| 4 | Enter postal code `12345` |
| 5 | Click **Continue** |
| 6 | Review the order overview (product details and total) |
| 7 | Click **Finish** |
| 8 | Wait for the confirmation page |

**Expected Result:**
- Checkout form accepts the provided information
- Order overview shows product name, price, quantity, tax, and total
- After clicking **Finish**, success message displayed: *"Thank you for your order!"*
- Confirmation page shows *"Order dispatched!"* and a **Back Home** button

---

## Tech Stack

- **Selenium WebDriver** 4.44.0
- **Cucumber** 7.34.3 (BDD / Gherkin)
- **JUnit 5** (JUnit Platform Suite runner)
- **ExtentReports** 5.1.1 (HTML test report)
- **Gradle** 8.x (build tool)
