package exercise.coding.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;

public class PointValueTest {

    @Test
    public void ofOnes() {

        assertThat(
                PointValue.of('a') +
                        PointValue.of('e') +
                        PointValue.of('i') +
                        PointValue.of('l') +
                        PointValue.of('n') +
                        PointValue.of('o') +
                        PointValue.of('r') +
                        PointValue.of('s') +
                        PointValue.of('t') +
                        PointValue.of('u')
        )
                .isEqualTo(10);
    }

    @Test
    public void ofTwos() {

        assertThat(
                PointValue.of('d') +
                        PointValue.of('g')
        )
                .isEqualTo(4);
    }

    @Test
    public void ofThrees() {

        assertThat(
                PointValue.of('b') +
                        PointValue.of('c') +
                        PointValue.of('m') +
                        PointValue.of('p')
        )
                .isEqualTo(12);
    }

    @Test
    public void ofFours() {

        assertThat(
                PointValue.of('f') +
                        PointValue.of('h') +
                        PointValue.of('v') +
                        PointValue.of('w') +
                        PointValue.of('y')
        )
                .isEqualTo(20);
    }

    @Test
    public void ofFives() {

        assertThat(
                PointValue.of('k')
        )
                .isEqualTo(5);
    }

    @Test
    public void ofEights() {

        assertThat(
                PointValue.of('j') +
                        PointValue.of('x')
        )
                .isEqualTo(16);
    }

    @Test
    public void ofTens() {

        assertThat(
                PointValue.of('q') +
                        PointValue.of('z')
        )
                .isEqualTo(20);
    }

    @Test
    public void ofInvalidCharacter() {

        assertThat(
                PointValue.of('+')
        )
                .isEqualTo(0);
    }

    @Test
    public void ofHat() {
        assertThat(PointValue.of("hat")).isEqualTo(6);
    }

    @Test
    public void ofLongestWord() {
        assertThat(PointValue.of("antidisestablishmenatarianism"))
                .isEqualTo(39);
    }
}