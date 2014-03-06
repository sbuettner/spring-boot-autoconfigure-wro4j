package de.infinit.spring.boot.autoconfigure.wro4j;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableApplicationContext context;

    private static int serverPort;

    @BeforeClass
    public static void start() throws Exception {
        Future<ConfigurableApplicationContext> future = Executors.newSingleThreadExecutor().submit(
            new Callable<ConfigurableApplicationContext>() {
                @Override
                public ConfigurableApplicationContext call() throws Exception {
                    return SpringApplication.run(TestApplication.class);
                }
            }
        );

        context = future.get(60, TimeUnit.SECONDS);
        serverPort = Integer.parseInt(context.getEnvironment().getProperty("server.port"));
    }

    @AfterClass
    public static void stop() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testJs() throws Exception {
        ResponseEntity<String> entity = getRestTemplate().getForEntity("http://localhost:"+serverPort+"/assets/app.js", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        String body = entity.getBody().trim();
        String jsBody = IOUtils.toString(new ClassPathResource("js/app.js").getInputStream()).trim();
        assertEquals("Assert that the javascript is the same.", jsBody, body);
    }

    @Test
    public void testCss() throws Exception {
        ResponseEntity<String> entity = getRestTemplate().getForEntity("http://localhost:"+serverPort+"/assets/app.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        String body = entity.getBody().trim();
        assertEquals("Assert that the css is combined and the less file compiled.", "p{text-indent:50px;}.box{color:#000000;}", body);
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });

        return restTemplate;
    }

}
