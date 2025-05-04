package io.github.protocol.bookkeeper.admin.jdk;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import io.github.openfacade.http.HttpClientEngine;
import io.github.protocol.bookkeeper.admin.api.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected final EmbeddedBookkeeperServer server = new EmbeddedBookkeeperServer();

    @BeforeAll
    public void setup() throws Exception {
        server.start();
    }

    @AfterAll
    public void teardown() throws Exception {
        server.close();
    }

    public Stream<Arguments> provideAutoRecoveries() {
        List<AutoRecoveryImpl> autoRecoveries = new ArrayList<>();
        Configuration conf = new Configuration();
        conf.host("localhost");
        conf.port(server.getBkWebPort());
        for (HttpClientEngine engine : HttpClientEngine.values()) {
            conf.engine(engine);
            AutoRecoveryImpl recovery = new AutoRecoveryImpl(new InnerHttpClient(conf));
            autoRecoveries.add(recovery);
        }
        return autoRecoveries.stream().map(recovery -> Arguments.arguments(recovery));
    }

    public Stream<Arguments> provideBookiesImpl() {
        List<Bookies> bookies = new ArrayList<>();
        Configuration conf = new Configuration();
        conf.host("localhost");
        conf.port(server.getBkWebPort());
        for (HttpClientEngine engine : HttpClientEngine.values()) {
            conf.engine(engine);
            Bookies bookie = new BookiesImpl(new InnerHttpClient(conf));
            bookies.add(bookie);
        }
        return bookies.stream().map(recovery -> Arguments.arguments(recovery));
    }

    public Stream<Arguments> provideConfigsImpl() {
        List<Configs> configs = new ArrayList<>();
        Configuration conf = new Configuration();
        conf.host("localhost");
        conf.port(server.getBkWebPort());
        for (HttpClientEngine engine : HttpClientEngine.values()) {
            conf.engine(engine);
            Configs config = new ConfigsImpl(new InnerHttpClient(conf));
            configs.add(config);
        }
        return configs.stream().map(recovery -> Arguments.arguments(recovery));
    }
}
