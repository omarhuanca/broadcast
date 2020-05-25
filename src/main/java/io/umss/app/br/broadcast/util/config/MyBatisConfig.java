package io.umss.app.br.broadcast.util.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig
 *
 * @author Elio Arias
 * @since 1.0
 */
@Configuration
@MapperScan({ "io.umss.app.br.broadcast.admin", "io.umss.app.br.broadcast.message" })
public class MyBatisConfig {

}
