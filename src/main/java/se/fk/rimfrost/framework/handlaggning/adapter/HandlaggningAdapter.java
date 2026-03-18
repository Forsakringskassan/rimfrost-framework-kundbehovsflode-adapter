package se.fk.rimfrost.framework.handlaggning.adapter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import se.fk.rimfrost.framework.handlaggning.model.Handlaggning;
import se.fk.rimfrost.framework.handlaggning.model.HandlaggningRead;
import se.fk.rimfrost.framework.handlaggning.model.Yrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.HandlaggningControllerApi;
import java.util.UUID;
import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@SuppressWarnings("unused")
@ApplicationScoped
public class HandlaggningAdapter
{

   @ConfigProperty(name = "handlaggning.api.base-url")
   String handlaggningBaseUrl;

   private HandlaggningControllerApi handlaggningClient;

   @Inject
   HandlaggningMapper handlaggningMapper;

   @PostConstruct
   void init()
   {
      ClientConfig clientConfig = new ClientConfig();
      clientConfig.connectorProvider(new ApacheConnectorProvider());
      Client client = ClientBuilder.newClient(clientConfig);
      this.handlaggningClient = WebResourceFactory.newResource(
            HandlaggningControllerApi.class,
            client.target(this.handlaggningBaseUrl));
   }

   public Yrkande createYrkande(Yrkande yrkande)
   {
      var postYrkandeRequest = handlaggningMapper.toPostYrkandeRequest(yrkande);
      var postYrkandeResponse = handlaggningClient.postYrkande(postYrkandeRequest);
      return handlaggningMapper.toYrkande(postYrkandeResponse.getYrkande());
   }

   public Handlaggning createHandlaggning(UUID yrkandeId, UUID processinstansId, UUID handlaggningspecifikationId)
   {
      var postHandlaggningRequest = handlaggningMapper.toPostHandlaggningRequest(yrkandeId, processinstansId,
            handlaggningspecifikationId);
      LOGGER.info("createHandlaggning: postHandlaggningRequest " + postHandlaggningRequest);
      var postHandlaggningResponse = handlaggningClient.postHandlaggning(postHandlaggningRequest);
      LOGGER.info("createHandlaggning executed");
      return handlaggningMapper.toHandlaggning(postHandlaggningResponse.getHandlaggning());
   }

   public HandlaggningRead readHandlaggning(UUID handlaggningId)
   {
      var getHandlaggningResponse = handlaggningClient.getHandlaggning(handlaggningId);
      return handlaggningMapper.toHandlaggningRead(getHandlaggningResponse.getHandlaggning());
   }

   public Handlaggning updateHandlaggning(Handlaggning handlaggning)
   {
      var putHandlaggningRequest = handlaggningMapper.toPutHandlaggningRequest(handlaggning);
      var putHandlaggningResponse = handlaggningClient.putHandlaggning(handlaggning.id(), putHandlaggningRequest);
      return handlaggningMapper.toHandlaggning(putHandlaggningResponse.getHandlaggning());
   }

}
