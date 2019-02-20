# BooksHeaven
Online Book Store Sample
To run the project you will need to have apache Tomcat installed.

After you import the project

-Go to context.xml and change the database path to your path where your project is store
should be something like url = "jdbc:derby:/Users/user_name/Documents/work_space/WebIt/WebContent/res/BookStore_database"
- For testing the test driver class you need to download the JUnit4 jar file.
- For SSL you will need to change file server.xml file in Tomcat as follows
Add this to the servers web.xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
maxThreads="150" scheme="https" secure="true" SSLEnabled="true"
keystoreFile="${user.home}/name.keystore" keystorePass="123456"
clientAuth="false" sslProtocol="TLS" />
- And round line number 66 please uncomment
<Connector executor="tomcatThreadPool"
port="8080" protocol="HTTP/1.1"
connectionTimeout="20000"
redirectPort="8443" />
- Put the file name.keystore from res folder to your /user dir so that your browser can be certified
We have provided the server.xml file and the keystore file they can be found under res folder of the project you might have to change line 125 of the server.xml file depending on what you have named the project in our case the project name is web_it if you name it something else then you should go to that line and change the context on line 125 <Context docBase="web_it" path="/web_it" reloadable="true" source="org.eclipse.jst.jee.server:web_it"/></Host> to be your new name for the project.
And the issue we faced using derby is that, derby have some problem with Auto increment
We used the auto increment and randomly it gives us 1,2,3,401,501… due to some memory leak issue in derby and this can cause some issues when registering a new user and this messes or database sometimes.

*Disclaimer :
This project is part of a school group project and is provided as is for learning purposes
