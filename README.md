# Foodics Task

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

## Complete Flow Digram:
--------------------------------

