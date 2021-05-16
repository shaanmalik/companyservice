company service
===============

Spring boot app that reads requests to `POST /` with json payload containing company details, and stores it in DB

### Start service

```
mvn spring-boot:run
```

Service starts at `http://localhost:8080`

### Run integration and unit tests

```
mvn test
```

### Sample requests


Save company details:
```
POST /
body:
{
    "msg_id": 1,
    "company_name": "acme company",
    "registration_date": "2020-10-27T14:34:06.132Z",
    "score":4.9,
    "directors_count": 7,
    "last_updated": "2020-10-27T14:34:06.132Z"
}
```

Get company details:

```
GET /
result:
[
    {
        "msg_id": 1,
        "company_name": "acme company",
        "registration_date": "2020-10-27T14:34:06.132Z",
        "score":4.9,
        "directors_count": 7,
        "last_updated": "2020-10-27T14:34:06.132Z"
    }
]
```
