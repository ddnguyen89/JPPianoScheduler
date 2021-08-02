Title:
SchedulingApplication

Purpose:
This is a scheduling application is used to store user, customer, and appointment records in a MySQL database. This application will allow users to create and modify customer records, as well as appointment records. The application will also displays notifications of upcoming appointments once the user logs in and allow the user different methods to view appointments and generate a report based on customer, contact, and appointment records.

Author:
Davis Nguyen

Contact Information:
dnguy80@wgu.edu

Student Application Version:
QAM1

Date:
07/28/2021

---------------

IDE:
IntelliJ Community 2020.3

JDK Version:
Java SE 11.0.10

JavaFX Version:
JavaFX-SDK-11.0.2

MySQL Connector Driver Version:
mysql-connector-java-8.0.23

---------------

How to run program:
Above are all of the tools used to run and create the application.
Instructions on how to run this program will be based on the IntelliJ Community IDE.

You may chose to clone or download this project to your machine and open the project through the IDE.
A MySQL Connector Driver and JavaFX SDK is needed to run this application. The versions I've used are listed above. The Driver and SDK should automatically be in your external library, if not you will need to manual add them.
To do so:
- Navigate to 'Project Structure' (CRTL + ALT + SHIFT + S) and then to Libraries
- Click on the '+' to add Java lib
- Navigate to your java-sdk/lib folder and hit ok
    ex: C:\Program Files\Java\javafx-sdk-11.0.2\lib
- In the lib project, Click on the '+' to add class
- Navigate to your mysql-connector-java.jar file and hit ok
    ex: C:\Program Files\MySQL\mysql-connector-java-8.0.23\mysql-connector-java-8.0.23.jar

Your project must also have a path pointing to your JavaFX SDK set.
To do so:
- Navigate to 'Run' > 'Edit Configurations...'
- Click on 'Modify Options' > 'Add VM Options' (Alt + V)
- Under VM Options, add the path to your JavaFX SDK lib:
    --module-path "<path>\javafx-sdk-11.0.2\lib" --add-modules javafx.fxml,javafx.controls,javafx.graphics
        ex: --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.fxml,javafx.controls,javafx.graphics
- Hit ok

Build and run the application.

Username / Password for logging into SchedulingApplication:
test / test
admin / admin

---------------

Reports Description:
ReportsMonth
    This report is used to track the total number of appointments by type for each month. This includes the month, appointment type, and the total number of appointment types by month.

ReportsContact
    This report displays all contacts' schedule. This includes the contact's name, appointment ID, title, type, description, date, start and end time, and customer ID.

ReportsCMTotal
    This report tracks the number of appointments by month for each contact. This includes the contact's name, the month, and the total number for appointments the contact may have for each month if applicable.

---------------

Lambda expressions used in the Formatter.java class under 'Resources'.
Line 30 - numberValidationFormatter
Line 51 - hourTimeFilter
Line 65 - minuteTimeFilter

Lambda expressions used in the MainController.java class under 'Controller'.
Line 460 - deleteAppointmentBTNOA
Line 526 - deleteCustomerBTNOA