# CredHub Test

## Overview

The goal of this demo application is to show that application code that retrieves credentials can remain the same regardless of whether the credentials come from a user-defined service or a CredHub service. It retrieves the credentials for a service names "database1".

## Create the user-defined service

You can use the following command-line to create a user-defined service:

`cf create-user-provided-service database1 -p '{"username":"admin","password":"password1234"}'`

## Build and deploy the application

You can build the application using (from within the project directory):

`mvn clean package`

and then deploy to PCF using:

`cf push credhub-test -p target/credhub-test-0.0.1-SNAPSHOT.jar`

## Test the application

Navigate to the application URL with a web browser. You should see (null) for both username and password.

## Bind the service and restart

The service needs to be bound to the application

`cf bind-service credhub-test database1`

You will need to restart the application after binding:

`cf restart credhub-test`

## Test the application

Navigate to the application URL with a web browser. You should see the expected values for both username and password.

In Apps Manager, look at the environment variables for the deployed application. Note that the VCAP_SERVICES contains a username and password in plain-text.

## Unbind and delete the user-defined service

`cf unbind-service credhub-test database1`

`cf delete-service database1`

## Create the CredHub service

You can use the following command-line to create a CredHub service (note that the CredHub Service Broker tile needs to be installed via Ops Manager):

`cf create-service credhub default database1 -c '{"username":"admin","password":"password1234"}'`

## Bind the service and restart

The service needs to be bound to the application

`cf bind-service credhub-test database1`

You will need to restart the application after binding:

`cf restart credhub-test`

## Test the application

Navigate to the application URL with a web browser. You should see the expected values for both username and password without having modified or re-deploying the application.

In Apps Manager, look at the environment variables for the deployed application. Note that the VCAP_SERVICES has a credhub-ref rather than username and password so no credentials are exposed.
