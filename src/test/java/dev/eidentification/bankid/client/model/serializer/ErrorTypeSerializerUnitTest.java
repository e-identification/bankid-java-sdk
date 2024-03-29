package dev.eidentification.bankid.client.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import dev.eidentification.bankid.UnitTest;
import dev.eidentification.bankid.client.model.enums.ErrorCode;
import dev.eidentification.bankid.client.model.ErrorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

class ErrorTypeSerializerUnitTest extends UnitTest {

    private final ErrorTypeSerializer converter = new ErrorTypeSerializer();

    @Mock
    private JsonParser jsonParser;
    @Mock
    private ObjectCodec objectCodec;
    @Mock
    private JsonNode jsonNode;
    @Mock
    private DeserializationContext context;

    @ParameterizedTest
    @MethodSource("deserializeMethodSource")
    void deserialize(final String codeValue, final ErrorType expected) throws IOException {
        Mockito.when(jsonParser.getCodec())
            .thenReturn(objectCodec);
        Mockito.when(objectCodec.readTree(any()))
            .thenReturn(jsonNode);
        Mockito.when(jsonNode.asText())
            .thenReturn(codeValue);

        var result = converter.deserialize(jsonParser, context);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void givenUnknownCodeValue_whenDeserialize_thenExpectedErrorType() throws IOException {
        Mockito.when(jsonParser.getCodec())
            .thenReturn(objectCodec);
        Mockito.when(objectCodec.readTree(any()))
            .thenReturn(jsonNode);
        Mockito.when(jsonNode.asText())
            .thenReturn("unknown");

        var result = converter.deserialize(jsonParser, context);

        Assertions.assertEquals(result.codeValue(), "unknown");
        Assertions.assertEquals(result.code(), ErrorCode.UNKNOWN);
    }

    private static Stream<Arguments> deserializeMethodSource() {
        return Arrays.stream(ErrorCode.values())
            .map(it -> Arguments.of(it.getCode(), ErrorType.of(it.getCode(), it)));
    }
}