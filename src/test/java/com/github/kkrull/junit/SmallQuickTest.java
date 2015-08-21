package com.github.kkrull.junit;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SmallQuickTest {
    @Test
    public void runsJUnitTests() throws Exception {
        assertThat(42, equalTo(2));
    }
}
