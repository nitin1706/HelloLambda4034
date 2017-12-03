# HelloLambda4034

This is a sample project integrating AWS IOT Button with AWS Lambda function.

This is implemented strictly for the purpose of playing around with AWS IOT Button.

Architecture:

IOT Button ->  (triggers) AWS Lambda function -> Lambda function will store the data to AWS RDS instance using old and gold JDBC connection.

** This code just saves the data to MySQL DB on RDS instance. I am playing around with this IOT button to configure it to send SMS and email notification based on type of Click happened from Button, I have been able to achieve the notification, but using a seperate Lambda function which is default provided by AWS using Node.js, what I am trying to do is using the same lambda function, take different actions based on click type happened on Button. **
