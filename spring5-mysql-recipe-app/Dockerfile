FROM centos
# "-y" defaults the questions to Yes when downloading and installing java with
# yum so it does a silent install of the yum
RUN yum install -y java
# "/tmp" is where spring boot will store temp files
# Tomcat stuff will not execute properly without /tmp
# It's a default that spring boot utilizes
VOLUME /tmp
ADD target/spring5-recipe-app-0.0.1-SNAPSHOT.jar my-recipe-app.jar
# A shot command that touches the jar file to update the date on the jar file
# It is very important if there are static resources such as images or things
# like that. Do this will change the date for those so if they get cached or
# something the new version will get pulled down
RUN sh -c 'touch /my-recipe-app.jar'
# Help tomcat start faster
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/my-recipe-app.jar"]