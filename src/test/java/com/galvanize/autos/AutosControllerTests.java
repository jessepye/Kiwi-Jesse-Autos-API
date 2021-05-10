package com.galvanize.autos;

public class AutosControllerTests {
    // GET /api/autos:
        // GET: /api/autos returns list of all autos in database
        // GET: /api/autos no autos in database returns 204 no content
        // GET: /api/autos?color=red returns all autos where color=red
        // GET: /api/autos?make=ford returns all autos where make=ford
        // GET: /api/autos?make=chevrolet&color=blue returns all blue chevrolets
    // POST /api/autos:
        // adds an automobile to the database, returns newly created auto
        // returns error message (400) upon a bad request
    // GET /api/autos/{vin}
        // Returns the requested automobile
        // Returns NoContent (204) if the vin is not found
    // PATCH /api/autos/{vin} - updates owner or color of vehicle
        // returns updated vehicle (200)
        // returns 204 if vehicle not found
        // returns 400 if given a bad request
    // DELETE /api/autos/{vin} - delete an automobile by its vin
        // returns 202 when delete request is accepted
        // returns 204 if vehicle is not found
}
