package kringlooptilburg.nl.productimageservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.net.InetSocketAddress;
import com.datastax.driver.core.Cluster;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

//    @Override
//    public String getContactPoints() {
//        return "127.0.0.1";
//    }
    @Override
    protected String getKeyspaceName() {
        return "productimages";
    }
//    @Override
//    protected int getPort() {
//        return 9042;
//    }

    @Bean
    public Cluster cluster() {
        return Cluster.builder()
                .addContactPointsWithPorts(
                        new InetSocketAddress("127.0.0.1", 9042),
                        new InetSocketAddress("127.0.0.1", 9043),
                        new InetSocketAddress("127.0.0.1", 9044)
                )
                .build();
    }
}