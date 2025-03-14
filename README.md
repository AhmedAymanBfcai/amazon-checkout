# Foodies Task

## Prerequisites
- Java 11 or higher
- Maven 3.6+

## Setup
1. Clone the repository
2. Update ShippingData with your Amazon credentials and address
3. Run `mvn install` to download dependencies

## Running Tests
- For Selenium tests: `mvn test -Dtest=AmazonShoppingTest`
- For API tests: `mvn test -Dtest=UserApiTests`

## Notes
- The project uses Page Object Model for Selenium tests
- API tests use RestAssured with TestNG
- Error handling and logging are implemented
- No order placement