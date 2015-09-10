package com.github.kkrull.jbehave.fixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

final class TestFixture {
    private static int _numTimesSetup = 0;

    public static void setup() {
        _numTimesSetup++;
    }

    public static void shouldHaveBeenSetUpOnce() {
        assertThat(_numTimesSetup, equalTo(1));
    }
}
