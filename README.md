
## Reward Points API

Building a Spring Boot RESTful API that calculates reward points for customers based on their transactions. The reward logic is:

## Use Case

2 points for every dollar spent over $100

1 point for every dollar spent between $50 and $100


## Technologies Used

- Java 1.8
- Spring Boot 2.4.4
- Maven
- JUnit 5
- SLF4J + Logback

## Solution

Created a simple application with:

- A Customer and Transaction model

- A RewardService to calculate rewards

- A RewardController with a RESTful endpoint /rewards

- Hardcoded mock data in service class for demonstration

- Time range filter (startDate and endDate as parameters)


##  API Endpoint
GET /api/v1/rewards/1?startDate=2025-03-01&endDate=2025-05-31

Example Request
GET /api/v1/rewards/1?startDate=2025-03-01&endDate=2025-05-31

Example Response

```json 
{
  "customerId": 1,
  "customerName": "Alice",
  "startDate": "2025-03-01",
  "endDate": "2025-05-31",
  "transactions": [
    {"date": "2025-03-10", "amount": 120.0},
    {"date": "2025-04-15", "amount": 80.0},
    {"date": "2025-05-05", "amount": 150.0}
  ],
  "monthlyRewards": {
    "MARCH": 90,
    "APRIL": 30,
    "MAY": 150
  },
  "totalRewards": 270
}
```

## Added below validation and error handling for the startDate and endDate parameters in the Spring Boot REST API

1. Validated date format yyyy-MM-dd (handle parsing errors)

2. Ensure startDate ≤ endDate

3. Handle non-existent customer ID

4. Return meaningful HTTP status codes and error messages


Example Error Responses

Invalid Date Format
GET /api/v1/rewards/1?startDate=2025-03-XX&endDate=2025-05-31

```json 
{
  "error": "Invalid date format. Use yyyy-MM-dd"
}
```

## We have used SLF4J logging which is default in springboot application for best practise.

## Added below test cased using JUnit 5 + Spring Boot Test to validate the reward calculation logic and REST API behavior under multiple scenarios.

RewardService — core business logic

RewardController — REST API behavior with date validation and response structure






