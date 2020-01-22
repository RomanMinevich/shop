This is a template of an E-STORE.

It has login and registration forms. There are controllers for working with items, users, orders and buckets:
Inject - for injection mock data,
Registration - for registering new users,
Login -  for user authentication and authorization,
Users - for displaying a list of all app users. Available for users with an ADMIN role only,
Items - for displaying  all items in stock,
Bucket - for displaying  user’s bucket. Available for users with a USER role only,
Orders - for displaying user’s order history. Available for users with a USER role only,
Logout - for logging out.

# <a name="Stack"></a>Stack
* Java 11
* Maven 4.0.0
* hibernate 5.4.5.Final
* javax.servlet 3.1.0
* jstl 1.2
* log4j 1.2.17
* maven-checkstyle-plugin
