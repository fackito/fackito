package com.fackito;

import com.fackito.stubs.User;
import org.junit.Test;

import static com.fackito.Fackito.fake;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

public class FackitoTest {

    @Test
    public void fakes_default_definition() {
        final User fake = fake(User.class);
        assertThat(fake.getName(), equalTo("Some Name"));
        assertThat(fake.getAge(), equalTo(31));
        assertThat(fake.getRoles(), hasItems("Role 1", "Role 1"));
        assertThat(fake.getAddress().size(), equalTo(4));
        assertThat(fake.getAddress(), hasEntry("line1", "My Address Line 1"));
        assertThat(fake.getAddress(), hasEntry("line2", "Address line 2"));
        assertThat(fake.getAddress(), hasEntry(equalTo("zip"), equalTo((Object) 20000)));
        assertThat(fake.getAddress(), hasEntry("city", "Washington D.C."));
        assertThat(fake.getPhoneNumber(), equalTo("(111) 222-3333"));
    }

    @Test
    public void fakes_random_name_definition() {
        final User fake = fake(User.class, "random_name");
        assertThat(fake.getName(), is(notNullValue()));
        assertThat(fake.getName(), not(equalTo("Some Name")));
        assertThat(fake.getAge(), equalTo(31));
        assertThat(fake.getRoles(), hasItems("Role 1", "Role 1"));
        assertThat(fake.getAddress().size(), equalTo(4));
        assertThat(fake.getAddress(), hasEntry("line1", "My Address Line 1"));
        assertThat(fake.getAddress(), hasEntry("line2", "Address line 2"));
        assertThat(fake.getAddress(), hasEntry(equalTo("zip"), equalTo((Object) 20000)));
        assertThat(fake.getAddress(), hasEntry("city", "Washington D.C."));
        assertThat(fake.getPhoneNumber(), equalTo("(111) 222-3333"));
    }

    @Test(expected = Exception.class)
    public void fakes_definition_not_found() {
        fake(User.class, "fake-not-found");
    }
}