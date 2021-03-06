package br.com.concretesolutions.requestmatcher.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;

@RunWith(RobolectricTestRunner.class)
@Config(constants = br.com.concretesolutions.requestmatcher.BuildConfig.class, sdk = 23)
public class ExceptionWitouhtEnqueuesTest {

    public final ExpectedException exceptionRule = ExpectedException.none();
    public final RequestMatcherRule server = new LocalTestRequestMatcherRule();

    @Rule
    public TestRule chain = RuleChain
            .outerRule(exceptionRule)
            .around(server);

    @Test
    public void ensureAProgrammingExceptionInTestIsPorperlyPropagated() throws IOException, InterruptedException {

        exceptionRule.expect(NullPointerException.class);
        Object nullRef = null;
        // throws NullPointerException in statement!!!
        nullRef.equals("");
    }
}
