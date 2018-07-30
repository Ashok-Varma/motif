package testcases.T020_naming_collisions;

import motif.Scope;

import javax.inject.Named;

import static com.google.common.truth.Truth.assertThat;

public class Test {

    public static void run() {
        Parent parent = new Test_GrandparentImpl().p();
        ChildA a = parent.a();
        ChildB b = parent.b();
        ChildC c = parent.c();
        ChildD d = parent.d();

        assertThat(a.string()).isEqualTo("a");
        assertThat(b.string()).isEqualTo("b");
        assertThat(c.c()).isNotNull();
        assertThat(d.d()).isNotNull();
    }

    @Scope
    interface Grandparent {

        Parent p();

        abstract class Objects {

            public abstract testcases.T020_naming_collisions.c.SomeDependency c();
            public abstract testcases.T020_naming_collisions.d.SomeDependency d();

            @Named("A")
            public String a() {
                return "a";
            }

            @Named("B")
            public String b() {
                return "b";
            }
        }
    }

    @Scope
    interface Parent {

        ChildA a();
        ChildB b();
        ChildC c();
        ChildD d();
    }

    @Scope
    interface ChildA {
        @Named("A")
        String string();
    }

    @Scope
    interface ChildB {
        @Named("B")
        String string();
    }

    @Scope
    interface ChildC {

        testcases.T020_naming_collisions.c.SomeDependency c();
    }

    @Scope
    interface ChildD {

        testcases.T020_naming_collisions.d.SomeDependency d();
    }
}