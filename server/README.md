## Setup
- Import [dump file](./db/db.sql) to your MySQL database
- Rename [this file](./src/main/resources/application-example.properties) to `application.properties`
- Then change `<<YOUR PASSWORD>>` to your mysql root password

## Usage
- To run the application:
  - For Windows: 
    ```bash
    ./gradlew.bat bootRun
    ```
  - For Linux/MacOS:
    ```bash
    ./gradlew bootRun
    ```