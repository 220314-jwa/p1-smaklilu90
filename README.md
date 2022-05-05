# SPMS: Story Pitch Management System

## Project Description

Story Pitch Management System is an application which allows authors to submit pitches for stories to the publishing company. They can write a small description of the story they hope to write, and editors can go into the application and approve or reject story pitches based on whether they sound interesting or even suggest changes before approval.

## Technologies Used

* Java SE - version 1.8
* PostgreSQL (local) - version 14.2
* JavaScript 
* HTML/CSS
* DBeaver - version 22.0
* Eclipse IDE - version 4.22.0
* VS Code- version 1.66.2
* Postman -version 9.17
* Junit - version 5.8.2
* Mockito- version 4.5.1
* Javalin- 4.3.0

## Features

List of features ready and TODOs for future development
* Users can register with a different role ( Author, Editor, Senior Editor) 
* Authors can create stories and submit them for approval 
* Editors can see all the stories that are submitted and review them 


To-do list:
* When the list of stories table populates it has a lot of ID columns, each Id represents a status, a role etc. If the user is not aware what the ID's represent it can lead to confusion. This can be improved by joining the related tables and create a better view in the Db 
* Editors and Senior editors should have a different role. i.e. for the time being they have the same roles/privileges.  

## Getting Started
   
**Requirements**
1. Download and Install PostgreSQL and DBeaver by following these links bellow (the links have specific instruction for your preferred OS  : 
  > https://www.postgresql.org/download/
  > https://dbeaver.io/download/
 
**Setting up the Environment and Getting Started**
2. Clone the repository by using the following command. You can run the command on  Terminal on (Linux, MAC) and Command Prompt(Windows). 
> `git clone https://github.com/220314-jwa/p1-smaklilu90`
3. navigate to the folder you clonned the repository and open the file named `dbscripts.sql` with any text editor and select and copy all the lines of code by pressing ctrl+A or cmd+A then ctrl+C/cmd+C (on Mac)  
4. Run dBeaver and click on the 🔌(plug) icon to create a new Db connection. 
5. On the connect to database wizard click on PostgresSQL and make sure the host set to **localhost** and the port is set to **5432** (if you didn't change anything, these settings are the default) 
6. in the password field put **1234** and click finish to create the connection 
7. On the navigation bar click click **SQL Editor** and click **new sql script**
8. you should have a sql editor blank  window in Dbeaver now. you paste the code that you copied in **Step 3** in there 
9. You can run the sql scripts one by one as shown bellow in the picture (I highly recommended that to avoid reference table conflicts). This will create all the required tables in the database for the Application to work. 
 ![dBever instruction](https://i.ibb.co/Dw7Tm1T/dbeaver.png)
10. Run Eclipse IDE and go to file-> import -> existing Maven project and browse to the folder where you cloned the repository. 
11.  Run the Java application
![enter image description here](https://i.ibb.co/Q8cjXqx/eclipse.png)
12. Navigate to the folder where you cloned the repository, open the front-end folder and double click index.html. this will open the home page of the app 
## Usage

> The navigation in the App is pretty straight forward, the Nav bar elements and what they does is explained in the screen shot of the home page bellow 

![](https://i.ibb.co/ydFCpxj/home-page.png)


