# Monthly payment calculator
This project consists of two Programs, Calculate and MonthlyPaymentCalculatorApplication.

Calculate.java prints out each prospect with their name, total loan, yearly interest, and amount of years as well as monthly payment.

MonthlyPaymentCalculatorApplication.java runs a Spring Boot project with Thymeleaf and Maven in the web browser.

## Prerequisites for running the Spring Project
JDK 17 and JAVA_HOME environvment variable set

## Running the project locally
Clone the repository: 
```
git clone https://github.com/benjaaminh/MonthlyPaymentCalculator
```
Navigate to the cloned folder:
```
cd MonthlyPaymentCalculator
```
Run the project using the following command:
```
./mvnw clean spring-boot:run
```
Using a web browser, navigate to:
```
http://localhost:8080
```
Now the program is ready to use

## Running the project with docker
Clone the repository:
```
git clone https://github.com/benjaaminh/MonthlyPaymentCalculator
```
Navigate to the cloned folder:
```
cd MonthlyPaymentCalculator
```
Build the docker image:
```
docker build --tag java-docker
```
Run the project using the following command:
```
docker run -p 8080:8080 java-docker
```
Or if you are using Docker desktop, build the image and run it through desktop application
