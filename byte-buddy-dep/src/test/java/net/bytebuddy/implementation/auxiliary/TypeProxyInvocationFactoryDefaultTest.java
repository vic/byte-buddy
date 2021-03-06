package net.bytebuddy.implementation.auxiliary;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.test.utility.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class TypeProxyInvocationFactoryDefaultTest {

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private Implementation.Target implementationTarget;

    @Mock
    private TypeDescription typeDescription;

    @Mock
    private MethodDescription methodDescription;

    @Mock
    private MethodDescription.Token methodToken;

    @Mock
    private Implementation.SpecialMethodInvocation specialMethodInvocation;

    @Before
    public void setUp() throws Exception {
        when(methodDescription.asToken()).thenReturn(methodToken);
    }

    @Test
    public void testSuperMethod() throws Exception {
        when(implementationTarget.invokeDominant(methodToken)).thenReturn(specialMethodInvocation);
        assertThat(TypeProxy.InvocationFactory.Default.SUPER_METHOD.invoke(implementationTarget, typeDescription, methodDescription),
                is(specialMethodInvocation));
        verify(implementationTarget).invokeDominant(methodToken);
        verifyNoMoreInteractions(implementationTarget);
    }

    @Test
    public void testDefaultMethod() throws Exception {
        when(implementationTarget.invokeDefault(typeDescription, methodToken)).thenReturn(specialMethodInvocation);
        assertThat(TypeProxy.InvocationFactory.Default.DEFAULT_METHOD.invoke(implementationTarget, typeDescription, methodDescription),
                is(specialMethodInvocation));
        verify(implementationTarget).invokeDefault(typeDescription, methodToken);
        verifyNoMoreInteractions(implementationTarget);
    }
}
