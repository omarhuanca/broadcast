package io.umss.app.br.broadcast;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

import io.umss.app.br.broadcast.util.AElog;
import io.umss.app.br.broadcast.util.AEutil;

/**
 * DigitalonboardingtoolkitApplication 
 * 
 * @author Elio Arias
 * @since 1.0
 */
@SpringBootApplication
public class BroadcastApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastApplication.class);

    @Autowired
    private AEutil util;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        // Corre antes de todo
        // ....
        SpringApplication.run(BroadcastApplication.class, args);
        // Corre despues de todo
        // ....
    }

    // Corre despues de: SpringApplication.run(NotificatorSckApplication.class,
    // args);
    @PostConstruct
    public void runPostConstruct() {
        AElog.info1(logger, "El proceso runPostConstruct() fue invocado.");
    }

    // Corre despues de: public void runPostConstruct(). Despues de que se cargo
    // todo
    @Override
    public void run(String... args) throws Exception {
        AElog.info1(logger, "El proceso run(String... args) fue invocado.");
        util.loadData();
    }
}
