wtw-base
========

Whatever The Weather (WTW) - Database service sub system components.

##What is it
A multi-module maven project that provides a database persistence service within the WTW SOA application system. 

##Component modules
1. wtw-base-service: Database persistence service API. 
2. wtw-base-client: JAXRS service proxy for client side access to database persistence service API. 
3. wtw-base-rs: JAXRS database persistence service access.
4. wtw-base-jpa: Database persistence service API JPA implementation.

##Architectural principles
1. Modularity.
2. Encapsulation, separation of concerns, Loose coupling.
3. Flexible, Extensible.
4. Distributed processing
5. Asynchronous processing
6. Variety of front ends, web, mobile, desktop
7. SOA, Web Services
8. Cloud deployment.
9. Continuous Integration, build, test, (unit, integration, UAT, performance, smoke test,) deploy.
10. Source code management with GIT, branches and master development.
11. Architect for OSGi.

## What does it look like?
wtw will be deployed on linode with UI currently prototyping on www.jimomulloy.co.uk:4000
