package com.sharkbaitextraordinaire.location.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;

import org.junit.Ignore;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class OwntracksUpdateTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Test
    public void serializeToJSON() throws Exception {
        final OwntracksUpdate update = new OwntracksUpdate("location", "45.5367495", "-122.6217988", "50.0", "81", 1437014122L);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/owntracksupdate.json"), OwntracksUpdate.class));

        assertThat(MAPPER.writeValueAsString(update)).isEqualTo(expected);
    }

    @Test
    public void deserializeFromJSON() throws Exception {
        final OwntracksUpdate update = new OwntracksUpdate("location", "45.5367495", "-122.6217988", "50.0", "81", 1437014122L);
        OwntracksUpdate readValue = MAPPER.readValue(fixture("fixtures/owntracksupdate.json"), OwntracksUpdate.class);

        assertNotNull(readValue);
        assertThat(readValue.get_type()).isEqualTo(update.get_type());
        assertThat(readValue.getLat()).isEqualTo(update.getLat());
        assertThat(readValue.getLon()).isEqualTo(update.getLon());
        assertThat(readValue.getAcc()).isEqualTo(update.getAcc());
        assertThat(readValue.getBatt()).isEqualTo(update.getBatt());
        assertThat(readValue.getTst()).isEqualTo(update.getTst());
    }

    @Test
    public void deserializeWithEnterEventFromJSON() throws Exception {
        final OwntracksUpdate update = new OwntracksUpdate("location", "45.5367495", "-122.6217988", "50.0", "94", 1443486427L);
        OwntracksUpdate readValue = MAPPER.readValue(fixture("fixtures/OwntracksUpdateWithEnterEvent.json"), OwntracksUpdate.class);

        assertNotNull(readValue);
        assertThat(readValue.get_type()).isEqualTo(update.get_type());
        assertThat(readValue.getLat()).isEqualTo(update.getLat());
        assertThat(readValue.getLon()).isEqualTo(update.getLon());
        assertThat(readValue.getAcc()).isEqualTo(update.getAcc());
        assertThat(readValue.getBatt()).isEqualTo(update.getBatt());
        assertThat(readValue.getTst()).isEqualTo(update.getTst());

    }
}
