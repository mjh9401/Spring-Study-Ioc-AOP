package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {

    // operand1=10
    @Test
    void createTest() {
        QueryString queryString = new QueryString("operand1","10");

        assertThat(queryString).isNotNull();
    }
}
