# Patient microservice

**Patients microservice is a service that manages patients from Abernathy Clinic.
Thanks to this API, you will be able to create, read, update and delete  patients.**

## Technical Stack
Patient microservice is built with the followings technologies :
- Java 17 and Spring Framework 3.0.1
- MySQL for database management system (db : patients)
- Maven
- Fein client (cloud) to connect this microservice to the web interface


# Setting up the microservice
In order to store datas, you must create a MySQL database with the following steps with your terminal :
- Create a database named `abernathyclinic` : `CREATE DATABASE abernathyclinic;` (default credentials are root:root and can be changed in `application.properties`)
-`USE abernathyclinic;`
- Load the schema (found in `src/main/resources`) using : `SOURCE schema.sql;`

If everything is set up correctly, start your (IDE) like intelliJ and start doing your crud.

Ps: you can test your patient microservice with Post man to be sure that you application fullfils the requirements.
if you don't already have post man. You can download it through : https://www.postman.com/downloads/

# API Specifications
**Microservice url is configured to serve datas on `localhost:8080/patient/`**.

## **GET `/patient`**
This route gets the list of every patients registered in database

### **Successful response example**
**URL : `localhost:8080/patient`**
```json
[
    {
        "id": "1",
        "nom": "Ben",
        "Prénom": "Hamza",
        "dateDeNiassance": "16/05/1995",
        "genre": "Homme",
        "address": "123 rue de Toulouse",
        "phone": "0766452555"
    },
    {
       "id": "2",
        "nom": "Ben",
        "Prénom": "Eric",
        "dateDeNiassance": "16/05/1999",
        "genre": "Homme",
        "address": "123 rue de Lyon",
        "phone": "0755774488"
    },
    {
         "id": "3",
        "nom": "Ben",
        "Prénom": "rashfold",
        "dateDeNiassance": "17/02/1991",
        "genre": "Homme",
        "address": "123 rue de Paris",
        "phone": "0711223355"
    }
]
```

## **GET `/patient/{id}`**
This route gets you a patient by its ID. Therefore, only one patient will be returned from this route, if the patient exists.

### **Successful response example**
**URL : `localhost:8080/patient/19/`**
```json
{
    "id": "19",
    "nom": "Ben",
    "prenom": "Dubois",
    "dateDeNaissance": "20/05/1993",
    "gender": "Homme",
    "address": "789 rue alphonso 4",
    "phone": "0744112255"
}
```

## **POST `/patient/`**
This route will create a patient for you. Field uses validation concept, therefore, any kind of filed error will throw an error.  

### **Successful content example**
**URL : `localhost:8080/patient/`**
```json
{
       "id": "5",
    "nom": "Ben",
    "prenom": "Caragere",
    "dateDeNaissance": "20/05/1997",
    "gender": "Homme",
    "address": "789 rue alphonso 4",
    "phone": "*********"
}
```

### **400 Bad Request**
```json
{
    "time": "2022-11-15T17:44:11.668+00:00",
    "status": "BAD_REQUEST",
    "message": "phone filed is required"
}
```

## **PUT `/patients/{id}/`**
This route updates a patient given its ID. Fields are validated and a custom error message will be returned if there are any. The following JSON can be used in the body to send datas (the same that is used on POST route) :

### **Successful content example**
**URL : `localhost:8080/patient/5/`**
```json
{
  {
       "id": "5",
    "nom": "Ben",
    "prenom": "Caragere",
    "dateDeNaissance": "20/05/2000",
    "gender": "Homme",
    "address": "789 rue reyalto 17",
    "phone": "0755223366"
}
}
```


## **DELETE `/patient/{patientId}`**
This route deletes a patient from a database based on an Id. 
A message will indicate if the operation is successful.

### **Successful response example**
**URL : `localhost:9001/patient/5/`**
```json
patient supprimé avec succée.
```
