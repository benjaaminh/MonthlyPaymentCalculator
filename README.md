# Monthly payment calculator
This project consists of two Programs, Calculate and MonthlyPaymentCalculatorApplication.

Calculate.java, located in the baseApp package contains all calculations and prints out each prospect with their name, total loan, yearly interest, and amount of years as well as monthly payment.
Calculate.java uses the FileReader class to resolve the path for the .txt file and read the number of lines in it.

MonthlyPaymentCalculatorApplication.java runs a Spring Boot project with Thymeleaf, Maven and Bootstrap in the web browser. The webapp uses an in-memory H2 database, so deleting and adding prospects will not cause damage.
The InitialData class reads the prospects.txt file and returns an array of all prospects as initial data for the webapp.

## Running the project locally

### Prerequisites for running locally
JDK 17 and [JAVA_HOME environment variable set](https://www.baeldung.com/java-home-on-windows-mac-os-x-linux)

Clone the repository: 
```
git clone https://github.com/benjaaminh/MonthlyPaymentCalculator
```
Navigate to the cloned folder:
```
cd MonthlyPaymentCalculator
```
Build and run the project using the following command:
```
./mvnw clean spring-boot:run
```
### Note: if building does not work, change line endings of mvnw file from CRLF to LF. This can be done from the bottom right of most editors. Remember to save the file!

Using a web browser, navigate to:
```
http://localhost:8080
```
Now the program is ready to use

## Running the project with docker

### Prerequisites for running with docker
Install docker desktop for your system

Run the docker desktop app to start the docker engine

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
docker build --tag java-docker .
```
### Note: if building does not work, change line endings of mvnw file from CRLF to LF. This can be done from the bottom right of most editors. Remember to save the file!

Run the project using the following command:
```
docker run -p 8080:8080 java-docker
```
Using a web browser, navigate to:
```
http://localhost:8080
```
Or if you are using Docker desktop, build the image and run it through desktop application with port set to 8080
