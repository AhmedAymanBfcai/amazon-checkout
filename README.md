# Foodies Task

## Prerequisites
- Java 11 or higher
- Maven 3.6+

## Setup
1. Clone the repository
2. Update the ShippingData.json file with your Amazon credentials and address
3. Run `mvn install` to download dependencies

## Running Tests
- For Selenium tests: `mvn test -Dtest=AmazonCheckoutTest`
- For API tests: `mvn test -Dtest=ReqRestApi`

## Notes
- The project uses Page Object Model for Selenium tests
- API tests use RestAssured with TestNG
- Error handling and logging are implemented
- No order placement

## Video while running tests:
- https://www.loom.com/share/663fa4b5e0fb44898e83f44abadf8041

## Test Video Walkthrough  
[Watch the video walkthrough](https://www.loom.com/share/663fa4b5e0fb44898e83f44abadf8041)


## Complete Flow:
--------------------------------
START
  |
  v
[Open https://www.amazon.eg/] (Step 1)
  |
  v
[Sign In] (Step 2)
  | - Enter phone number
  | - Enter password
  | - Submit
  v
[Open "All" Menu] (Step 3)
  |
  v
[Click "Video Games" -> "All Video Games"] (Step 4)
  |
  v
[Apply Filters] (Step 5)
  | - Select "Free Shipping"
  | - Select "New" condition
  v
[Sort by Price: High to Low] (Step 6)
  |
  v
[Add Products < 15k EGP to Cart] (Step 7)
  | - Loop through products on current page
  |   | - Price < 15k EGP AND "Add to Cart" button enabled?
  |   |   Yes -> Add product to cart
  |   |   No  -> Skip to next product
  | - All products checked on page:
  |   | - Cart has items? -> Proceed
  |   | - No items < 15k? -> Go to next page -> Repeat loop
  | - No more pages AND no items added? -> Fail test
  v
[Verify Products in Cart] (Step 8)
  | - Check cart contains all expected products
  |   Yes -> Proceed
  |   No  -> Fail test
  v
[Go to Checkout] (Step 9)
  |
  v
[Add Address] (Step 10)
  | - Enter full name, phone, street, etc.
  | - Submit address
  v
[Check COD Availability] (Step 11)
  | - COD radio button enabled?
  |   Yes -> Select COD -> Proceed
  |   No  -> Print "COD is not available for all products added" -> Fail test
  v
[Verify Total Amount] (Step 12)
  | - Cart subtotal + COD fee (12 EGP, if applicable) = Final total?
  |   Yes -> Success
  |   No  -> Fail test
  v
END
