package com.fackito;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static com.fackito.Fackito.fake;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

public class FakitoTest {

    @Test
    public void testFake() {
        final User fake = fake(User.class);
        assertThat(fake.getName(), is(notNullValue()));
        assertThat(fake.getAge(), Matchers.greaterThan(0));
        assertThat(fake.getAge(), Matchers.lessThan(120));
        final List<String> roles = fake.getRoles();
        assertThat(roles.size(), equalTo(2));
        for (String role : roles) {
            assertThat(role, is(notNullValue()));
            assertThat(role.trim(), not(""));
        }
        assertThat(fake.getAddress().size(), equalTo(4));
        assertThat(fake.getAddress(), hasEntry("line1", "My Address Line 1"));
        assertThat(fake.getAddress(), hasEntry("line2", "Address line 2"));
        assertThat(fake.getAddress(), hasEntry(equalTo("zip"), equalTo((Object) 20000)));
        assertThat(fake.getAddress(), hasEntry("city", "Washington D.C."));
        System.out.println(fake.getPhoneNumber());
        assertThat(fake.getPhoneNumber(), is(notNullValue()));
    }
}