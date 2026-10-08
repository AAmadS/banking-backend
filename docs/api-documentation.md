# Banking Backend API Documentation

## Overview 

Banking Backend is a REST API built with Java, Spring Boot, Spring Data JPA, and PostgreSQL.

The API supports customer management, bank accounts, deposits, withdrawals, transfers, and transaction filtering.

## Base URL

The API runs locally at: 

http://localhost:8080/api

The Spring Boot application must be running to access the endpoints.

For interactive API documentation, visit:

http://localhost:8080/swagger-ui/index.html

## Customer Management 

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers` | Retrieve all customers |
| GET | `/api/customers/{id}` | Retrieve a customer by ID |
| PUT | `/api/customers/{id}` | Update a customer's contact information |
| DELETE | `/api/customers/{id}` | Delete a customer if they have no open accounts |
| GET | `/api/customers/{id}/accounts` | Retrieve all accounts belonging to a customer |

## Account Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts` | Create a new account for an existing customer |
| GET | `/api/accounts/{acctNo}` | Retrieve an account by its account number |
| PUT | `/api/accounts/{acctNo}` | Update an account's status |
| DELETE | `/api/accounts/{acctNo}` | Close an account if its balance is zero |

## Transaction Processing

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transactions` | Create a deposit or withdrawal |
| GET | `/api/transactions/{id}` | Retrieve a transaction by ID |
| GET | `/api/accounts/{acctNo}/transactions` | Retrieve all transactions for an account |
| GET | `/api/transactions` | Retrieve and filter transactions |

## Funds Transfers

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transfers` | Transfer money between two accounts |

## Request Examples

### Create Customer

**POST** `/api/customers`

**Request Body:**

```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane@example.com",
  "phone": "5551234567"
}
```

### Update Customer Contact Information


**PUT** `/api/customers/{id}`

**Request Body:**

```json
{
  "email": "jane.new@example.com",
  "phone": "5559876543"
}
```

### Create Account

**POST** `/api/accounts`

**Request Body:**

```json
{
  "customerId": 5,
  "accountType": "CHECKING",
  "initialDeposit": 100.00
}
```

The customer must already exist. The account number is generated automatically, and the account starts with an `ACTIVE` status.


### Update Account Status

**PUT** `/api/accounts/{acctNo}`

**Request Body:**

```json
{
  "accountStatus": "SUSPENDED"
}
```

Valid statuses are `ACTIVE`, `SUSPENDED`, and `CLOSED`. An account can only be closed when its balance is zero. Suspended and closed accounts cannot perform transactions.



### Deposit Money

**POST** `/api/transactions`

**Request Body:**

```json
{
  "accountNumber": "7379148210",
  "transactionType": "DEPOSIT",
  "amount": 100.00
}
```

### Withdraw Money

**POST** `/api/transactions`

**Request Body:**

```json
{
  "accountNumber": "7379148210",
  "transactionType": "WITHDRAWAL",
  "amount": 50.00
}
```


### Transfer Money

**POST** `/api/transfers`

**Request Body:**

```json
{
  "sourceAccountNumber": "7379148210",
  "targetAccountNumber": "1343922921",
  "transferAmount": 50.00
}
```

Both accounts must exist, be active, and have different account numbers. The transfer amount must be positive, and the source account must have sufficient funds.

Transfers are atomic, meaning either both account balances are updated successfully or neither change is saved.


## Transaction Filtering

**GET** `/api/transactions`

This endpoint allows transactions to be filtered using optional query parameters.

| Parameter | Type | Description |
|-----------|------|-------------|
| `accountNo` | String | Filter by account number |
| `type` | String | `DEPOSIT` or `WITHDRAWAL` |
| `fromDate` | DateTime | Transactions on or after this date |
| `toDate` | DateTime | Transactions on or before this date |
| `minAmount` | Decimal | Minimum transaction amount |
| `maxAmount` | Decimal | Maximum transaction amount |

### Example Request

`GET /api/transactions?type=WITHDRAWAL&minAmount=100`

This returns withdrawals with an amount of at least $100.

All parameters are optional and can be combined.
Dates use ISO format, such as `2026-10-07T10:00:00`.



## Error Handling

The API returns appropriate HTTP status codes and consistent JSON error responses when a request fails.

### Common HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| `200 OK` | Request completed successfully |
| `400 Bad Request` | Invalid input, insufficient funds, or an invalid account operation |
| `404 Not Found` | Requested customer, account, or transaction does not exist |
| `409 Conflict` | Request conflicts with existing data or business rules |

### Example Error Response

```json
{
  "timestamp": "2026-10-07T16:02:48",
  "status": 400,
  "error": "Bad Request",
  "message": "Cannot deposit into an account that is not ACTIVE"
}
```

All handled errors follow the same structure:

- `timestamp`: When the error occurred
- `status`: HTTP status code
- `error`: HTTP error description
- `message`: Explanation of what went wrong
