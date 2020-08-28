package com.lme.robots.positioning;

import static com.lme.robots.positioning.Orientation.E;
import static com.lme.robots.positioning.Orientation.N;
import static com.lme.robots.positioning.Orientation.S;
import static com.lme.robots.positioning.Orientation.W;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrientationTest {

    @Test
    public void shouldGetCorrectLeftOrientation() {
        assertThat(N.getLeft(), equalTo(W));
        assertThat(S.getLeft(), equalTo(E));
        assertThat(E.getLeft(), equalTo(N));
        assertThat(W.getLeft(), equalTo(S));
    }

    @Test
    public void shouldGetCorrectRightOrientation() {
        assertThat(N.getRight(), equalTo(E));
        assertThat(S.getRight(), equalTo(W));
        assertThat(E.getRight(), equalTo(S));
        assertThat(W.getRight(), equalTo(N));
    }

}
