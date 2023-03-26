# URLShortener

<h2>About</h2>

This was a quick project to familiarise myself with Spring. It is a simple rest api that can receieve a post request of a website address and will in turn provide a shortened url. When using this url - the client will be redirected to the original website. The project is built with Spring and uses JPA to connect with a MySQL database hosted by the university I attend. The hashing alogirthm used is MD5 - which initially assigns the first 4 characters as the shortened link - as conflicts arise the link can grow by a character to a maximum of 32.

To add to this project I hope to come back and learn a way of caching frequently used tiny urls to speed up the service and to not rely on the slow database connection.

<h2>Key learnings</h2>

- Spring IOC
- JPA
- Spring Boot Controllers

**Making a tinyify request**
<br/>
![](markdownGifs/POST.gif "Creating a short URL")
 

**Using the tiny url and being redirected to the site**
<br/>
![](markdownGifs/GET.gif "Short URL redirect")
