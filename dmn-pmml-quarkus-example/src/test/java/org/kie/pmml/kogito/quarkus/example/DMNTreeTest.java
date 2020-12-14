/**
 *  Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.kie.pmml.kogito.quarkus.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DMNTreeTest {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    @Test
    public void testEvaluateTreeDMN() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post("/TestTreeDMN")
                .then()
                .statusCode(200)
                .body("TestTreeBKM", is("function TestTreeBKM( humidity, temperature )"))
                .body("temperature", is(Float.valueOf("30")))
                .body("humidity", is(Float.valueOf("10")))
                .body("Decision", is("sunglasses"));
    }
}
