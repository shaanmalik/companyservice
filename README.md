company service
===============

Spring boot app that reads requests to `POST /` with json payload containing company details, and stores it in DB

sample requests

Save company details:
```
POST /

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
