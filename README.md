# Description
This project is a working, ready to production example of use **Spring Boot**,
**Kotlin** and **OAuth2**.

Oauth2 work on JDBC user storage and token storage

## Datasource properties
**url**: jdbc:postgresql://localhost:5432/oauth2_test

**username**: oauth2_test

**password**: oauth2_test

You can see properties details in **resources/application.yml**

## Using
Step 1: Request for authorize and getting tokens : 

`curl -X POST --user 'client:secret' -d 'grant_type=password&username=login&password=password' http://localhost:8080/oauth/token`

WARNING: **username** and **password** are from real user record (**UserEntity**) in database, 
**client** and **secret** from **AuthorizationServerConfig**

Step 2: After you getting a **tokens**, you should use token to access to a secure resource.
For example, in **ResourceServerConfig** defined one entry point (**/api/****)

`curl -X GET -H 'Authorization: Bearer 2c29d195-bb24-44cc-8be2-0cdb1f1e7694' -d 'grant_type=password&username=login&password=password' http://localhost:8080/api/message`

Step 3: And you can use the **refresh token** from **Step 1** to generate new an access token:

`curl -X POST --user 'client:secret' -d 'grant_type=refresh_token&refresh_token=657eeeaa-835e-4d9e-8ece-4b7a77e6fa98' http://localhost:8080/oauth/token`
