package ru.junit.rules;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RunRepeatRule extends TestWatcher implements TestRule {

    @Override
    public Statement apply(Statement base, Description desc) {
        return new RunTwiceStatement(base, desc);
    }

    public class RunTwiceStatement extends Statement {

        private final Statement base;
        private Description desc;

        public RunTwiceStatement(Statement base, Description desc) {
            this.base = base;
            this.desc = desc;
        }

        @Override
        public void evaluate() {
            if (desc.getAnnotation(Unstable.class) != null) {
                int attempts = desc.getAnnotation(Unstable.class).attempt();
                System.out.println("Ustable test!!! Try execute test " + attempts + " times");

                for (int i = 0; i < attempts; i++) {
                    try {
                        base.evaluate();
                        System.out.println("Test SUCCESS");
                        break;
                    } catch (Throwable t) {
                        String error = "Failed attempt test execute : " + (i + 1);
                        if (i == attempts - 1) {
                            Assert.fail(error);
                        } else {
                            System.out.println(error);
                        }
                    }
                }

            } else {
                System.out.println("Stable test!!!");
            }
        }
    }
}
