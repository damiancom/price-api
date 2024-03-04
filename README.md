# __price-api__


### Application that provides information about product prices based on their brand and effective date

---

## __Technologies__
- Java 17
- Spring Boot
- Lombok
- H2
- Junit
- Mockito
- Gradle

## __Run the application__
You should clone the repository. You can use the following command

``` 
git clone git@github.com:damiancom/price-api.git
```
or
```
git clone https://github.com/damiancom/price-api.git 
```

Located in the root folder of the project, run the command:
```
gradle bootRun
```

### Considerations
- You should have:
  - Java 17 or higher installed in your environment.
  - Gradle 8.5.
  - Internet connection to download libraries from remote repositories that may be necessary to 
    compile and run the project.

---

## __API URLs__

### Local: http://localhost:8080/

## __Published Services__

### /api/v1/prices
Get the price of a product by its id, brandId and its effective date

``` 
GET /api/v1/prices?brand_id={brandId}&product_id={productId}&application_date={applicationDate}
```

### Query Parameters

- **brandId**: Brand id of price to be searched.
    - Type: Long
    - Required: Yes
- **productId**: Product id of price to be searched.
    - Type: Long
    - Required: yes
- **applicationDate**: Effective date for the price.
    - Type: DateTime
    - Date Time Format: ISO Date Time
    - Required: Yes

### Example

```bash
curl -X GET "http://localhost:8080/api/v1/prices?brand-id=1&product_id=35455&application_date=2020-06-15T12:00:00" 
```

#### Response
``` 
200 OK

{
  "price": 35.5,
  "brand_id": 1,
  "product_id": 35455,
  "price_id": 1,
  "start_date": "2020-06-14T00:00:00",
  "end_date": "2020-12-31T23:59:59"
}
```
---
```bash
curl -X GET "http://localhost:8080/api/v1/prices?product_id=35455&application_date=2020-06-15T12:00:00" 
```
#### Response
``` 
400 Bad Request

{
  "status": 400,
  "error": "Bad Request",
  "detail": "Required request parameter 'brand_id' for method parameter type Long is not present"
} 
```
---
```bash
curl -X GET "http://localhost:8080/api/v1/prices?brand_id=1&product_id=1&application_date=2020-06-15T12:00:00" 
```
#### Response
``` 
404 Not Found
```
---
```bash
curl -X GET "http://localhost:8080/api/v1/prices/" 
```
#### Response
``` 
500 Internal Server Error 

{
  "status": 500,
  "error": "Internal Server Error",
  "detail": "An error occurred"
}
```
