# Number to Text Converter Application

## Project Overview
REST API which converts long entries to text.

## Important

A billion is considered as 10¹², a trillion 10¹⁸ [see Long  and short scale](https://en.wikipedia.org/wiki/Long_and_short_scales)

Maximum allowed value: 9223372036854775807 (Long.MAX_VALUE)

Minimun allowed value: -9223372036854775807 (Long.MIN_VALUE+1)

The application only accepts integers, not decimal values and not allow symbols (except for the minus '-').

## General
#### Build
#### To create a runnable java artifact:
```
mvn clean package
```
### Running the app locally:

```
mvn spring-boot:run
```

### Make requests to the application:

#### CURL example request:

```
curl 'localhost:8080/convert/-987654321'
```

#### Swagger documentation:
Browse for:
http://localhost:8080/swagger-ui.html
There you can run queries, see the otuput model and more.

## Testing

```
mvn clean test
```

## Testing using Rest Assured

Build and run the app. Then, on another terminal console execute:
```
mvn clean test -Pintegration
```
