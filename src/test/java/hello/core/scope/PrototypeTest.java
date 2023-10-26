package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototrypeBean.class);
        System.out.println("find prototypeBean1");
        PrototrypeBean prototrypeBean1 = ac.getBean(PrototrypeBean.class);
        System.out.println("find prototypeBean2");
        PrototrypeBean prototrypeBean2 = ac.getBean(PrototrypeBean.class);
        System.out.println("prototrypeBean1 = " + prototrypeBean1);
        System.out.println("prototrypeBean2 = " + prototrypeBean2);
        assertThat(prototrypeBean1).isNotSameAs(prototrypeBean2);

//        prototrypeBean1.destroy();
//        prototrypeBean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    static class PrototrypeBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
