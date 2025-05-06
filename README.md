# scoreboard

This repository contains a solution for coding exercise.

## Coding Exercise

### Description
Please **provide the implementation of the Live Football World Cup Score Board** as a **simple
library**.

### Requirements

You are working in a sports data company, and we would like you to develop a new Live Football
World Cup Scoreboard library that shows all the ongoing matches and their scores.

**The scoreboard supports the following operations:**
1. Start a new match, assuming the initial score 0 – 0 and adding it the scoreboard.
   This should capture the following parameters: Home team and Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish the match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

### Assumptions

- Creating and storing matches in the scoreboard before they start and after they are finished can be beneficial for managing the lifecycle of matches and maintaining historical data. However, this might not be Scoreboards responsibility. In the provided solution, the Scoreboard does not store Match instances before a match starts and after it is completed.


### Guidelines

#### Running the Project

To build the project, ensure you have Java and Gradle installed on your system. You can start by compiling the code and running the project using the following command:

```bash
./gradlew build
```

#### Generating a test coverage report

To generate test coverage report run command:

```bash
./gradlew test jacocoTestReport
```

Test report will be located at: **/build/reports/jacoco/test/html/index.html**


