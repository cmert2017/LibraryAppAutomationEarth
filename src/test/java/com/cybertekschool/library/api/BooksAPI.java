package com.cybertekschool.library.api;

import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.Endpoints;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.api.StudentAuthenticationUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static com.cybertekschool.library.utils.api.Endpoints.*;
import static io.restassured.RestAssured.given;

public class BooksAPI {

    public Response addBook(Map<String, String> book) {
        // get a token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String librarianToken = authenticationUtility.getToken();
        Response response = given().
                header("x-library-token", librarianToken).
                formParams(book).
                log().all().
                when().
                post(ADD_BOOK).
                prettyPeek();
        response.then().statusCode(200);
        return response;
    }

    public Response editBook(Map<String,Object> putMap){
        // get a token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String librarianToken = authenticationUtility.getToken();
        //Map<String,Object> putMap = new HashMap<>();
        //putMap.put("name","NAME");
        Response response = given().contentType("application/json").
                accept(ContentType.JSON).
                header("x-library-token", librarianToken).
                body(putMap).
                when().patch(UPDATE_BOOK).
                prettyPeek();
        response.then().statusCode(200);
        return response;
    }
}
