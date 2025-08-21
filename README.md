
---

# Lottery Service

A simple lottery system built with **Spring Boot (Java 21)** and **PostgreSQL**.

The service allows users to register, submit ballots, create lotteries, and check winners.

---

## Tech Stack

* **Java 21**
* **Spring Boot**
* **Gradle**
* **PostgreSQL** (with Docker for local setup)

---

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/PSStoilova/lottery-system.git
   cd lottery-system
   ```

2. Start PostgreSQL with Docker:

   ```bash
   docker compose up -d
   ```

3. Run the application:

   ```bash
   ./gradlew bootRun
   ```

4. The server runs on [http://localhost:8080](http://localhost:8080).

---

## REST API

### Users

* `POST /api/v1/users` — Create a user
* `GET /api/v1/users/all` — Get all users

### Ballots

* `POST /api/v1/ballots` — Create a ballot
* `GET /api/v1/ballots/all` — Get all ballots for a user
* `GET /api/v1/ballots/day` — Get all ballots for the current day

### Lotteries

* `POST /api/v1/lotteries` — Create a lottery
* `GET /api/v1/lotteries/all` — Get all lotteries

---

## Notes

* Lotteries close daily at midnight and a winner is selected automatically.
* Data is persisted in PostgreSQL.

---