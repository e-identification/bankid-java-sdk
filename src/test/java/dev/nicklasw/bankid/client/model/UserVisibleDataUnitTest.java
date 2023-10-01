package dev.nicklasw.bankid.client.model;

import dev.nicklasw.bankid.UnitTest;
import dev.nicklasw.bankid.exceptions.BankIdRequirementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserVisibleDataUnitTest extends UnitTest {

    @Test
    void givenValidContent_whenOf_thenExpectedContent() {
        var result = UserVisibleData.of("Hello");

        Assertions.assertEquals("Hello", result.getContent());
    }

    @Test
    void givenEmptyContent_whenOf_thenExpectedException() {
        Assertions.assertThrows(BankIdRequirementException.class, () -> UserVisibleData.of(""));
    }

    @Test
    void givenToLongContent_whenOf_thenExpectedException() {
        Assertions.assertThrows(BankIdRequirementException.class, () -> UserVisibleData.of("A".repeat(40_001)));
    }

}