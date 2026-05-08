# Auction

A console-based auction simulation written in Java, featuring automated bidding agents, 
interactive user participation, and event-driven auction processing.


## Features

- Real-time console event logging
- Interactive and automated auction participants
- Automated participants with different bidding strategies (aggressive, balanced, passive) based on item preferences
- Event-driven communication between auction system and participants


## Architecture

The system follows an event-driven simulation design:
- **AppManager** orchestrates the entire application lifecycle and simulation loop.
- **AuctionHouse** controls the sequential execution of auctions.
- Each **Auction** manages the lifecycle of item sales using internal workflow logic.
- **Participants** react to auction events using the Observer Pattern and make decisions using pluggable bidding strategies.


## Application Flow

- **Initialization** - The application starts by setting up the simulation parameters including the number of auctions, 
items per auction, participant budgets, and whether to include an interactive user.

- **Creation** - Auctions are created, each with random items.
Automated participants are created with random item preferences and assigned bidding strategies. 
If enabled, an interactive user participant is also created.

- **Signing up for auctions** - Bidders choose whether to participate in auctions by matching auction items against their preferences.
If user is present in setup, he can decide via console.


- **Auction Loop** - Items are sold sequentially in each auction in a loop:
  - An item with a price is announced to participants
  - Participants place bids
  - If no one outbids the current bid, the item is sold. If no bids are placed, the item is not sold.

- **Completion** - The results are logged, showing each participant's bought items and remaining budget.


## Project Structure
**auction/**  
&nbsp; Auction engine and auction lifecycle management.

**communication/**  
&nbsp; Console interaction and centralized logging system.

**items/**  
&nbsp; Auction items and categories.

**observers/**  
&nbsp; Event notification system based on the Observer Pattern.

**people/**  
&nbsp; Human and automated participants.

**strategies/**  
&nbsp; Bidding strategies.

  
## Technologies

- Java
- Implemented Design Patterns:
  - Strategy
  - Observer
  - Singleton


## Configuration
Simulation parameters can be adjusted in 'Main.java':

```java
appManager.setup(
    3,        // number of auctions
    2,        // items per auction
    4,        // desired items per participant
    500,      // starting budget
    true      // enable interactive user
);
```

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA or another Java IDE
3. Run `Main.java`

Requirements:
- Java 17+
## Demo

https://github.com/user-attachments/assets/e61132cc-2a61-4029-9386-132eb9a46e38
