package org.example;

import org.junit.jupiter.api.*;

import java.io.IOException;

class ClientTest {
    private Client client;

    @BeforeEach
    public void initialize() throws IOException {
        client = new Client();
        client.connect(EchoServer.HOSTNAME, EchoServer.PORT);
    }

    @Test
    void shouldResponse() throws IOException {
        String firstMessage = "Test1";
        String secondMessage = "Test2";
        String goodbyeMessage = "q!";

        String firstServerResponse = client.sendMessage(firstMessage);
        String secondServerResponse = client.sendMessage(secondMessage);
        String lastServerResponse = client.sendMessage(goodbyeMessage);

        Assertions.assertEquals(firstServerResponse, firstMessage);
        Assertions.assertEquals(secondServerResponse, secondMessage);
        Assertions.assertEquals(lastServerResponse, "Good bye!");
    }

    @AfterEach
    public void tearDown() throws IOException {
        client.close();
    }
}
