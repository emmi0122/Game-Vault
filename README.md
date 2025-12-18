# Game-Vault
**Group 2**  
**Author:** Emilia Jarleback, Daniel Gran and Jessica Olofsson  

## Links
- [Project Info](#project-info)
- [Features](#features)
- [Technologies and Frameworks](#technologies-and-frameworks)
- [Project Structure](#project-structure)
- [How to Run the Project](#how-to-run-the-project)


## Project info

This project is made up of three micro services for game reviews and ratings with three main parts:  
* User-service (Port 8080) - Manages users and profiles
* Review-service (Port 8081) - Manages reviews and ratings
* Game-service (Port 8082) - Manages different games and platforms  

The project also contains a frontend (React + TypeScript) for visualization and interface.

## Features
* Login and/or create account
* Home-page for profile and a list of all games
* Create reviews for games, add rating

## Technologies and Frameworks 
* Java 21
* Spring Boot
* JPA
* Maven (for project structure and dependencies)
* JUnit
* Node + Vite + React

## Project Structure
```
game-vault/
├── frontend/               # React + TypeScript + Vite
├── game-service/           # Spring Boot - Game Management
├── review-service/         # Spring Boot - Reviews & Likes
└── user-service/           # Spring Boot - Authentication & Profiles  
```

### Frontend structure
```
frontend/
├── src/
│   ├── assets/             # Assets: Images 
│   ├── component/          # Small parts you see in the pages
│   ├── endpoints/          # Connection to the backend
│   ├── interfaces          # Declare types for incoming objects
│   ├── pages/              # Routing in the application 
│   ├── style/              # CSS for styling
│   ├── index.css           # Global CSS
│   └── main.tsx
├── index.html
└── vite.config.ts
```

### Microservice Global Structure
```
├── src/
│   ├── main/
│   │   ├── java/se/yrgo/
│   │   │   ├── config/
│   │   │   ├── rest/ or controller/
│   │   │   ├── domain/
│   │   │   ├── dto/
│   │   │   ├── data/ or repository/
│   │   │   ├── exception/
│   │   │   ├── service/
│   │   │   └── ...Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── run.sh
```

## How to Run the Project
### 1. Clone the repository:

```
git clone https://github.com/emmi0122/Game-Vault.git 

cd Game-Vault
```

### 2. To run the projekt make four terminals and go to these directories with cd
* `cd frontend`
* `cd user-service`
* `cd game-service`
* `cd review-service`

### 3. Build and Run
In these directories you can run the scripts with this prompt:  
```
./run.sh
```

This will run
```
mvn clean package
``` 

And will start springboot. Or if you are in the frontend directory:
```
npm run dev
``` 

**Note:** Make sure Java and Maven are installed on your machine and make sure you are authorized with:  
```
chmod u+x run.sh
```
