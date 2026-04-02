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
import se.fk.rimfrost.framework.handlaggning.model.HandlaggningUpdate;
import se.fk.rimfrost.framework.handlaggning.model.Handlaggning;
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
      var postHandlaggningRequest = handlaggningMapper.toPostHandlaggningRequest(yrkandeId,
            handlaggningspecifikationId);
      LOGGER.info("createHandlaggning: postHandlaggningRequest " + postHandlaggningRequest);
      var postHandlaggningResponse = handlaggningClient.postHandlaggning(postHandlaggningRequest);
      LOGGER.info("createHandlaggning executed");
      return handlaggningMapper.toHandlaggning(postHandlaggningResponse.getHandlaggning());
   }

   public Handlaggning readHandlaggning(UUID handlaggningId)
   {
      var getHandlaggningResponse = handlaggningClient.getHandlaggning(handlaggningId);
      return handlaggningMapper.toHandlaggning(getHandlaggningResponse.getHandlaggning());
   }

   public HandlaggningUpdate updateHandlaggning(HandlaggningUpdate handlaggningUpdate)
   {
      var putHandlaggningRequest = handlaggningMapper.toPutHandlaggningRequest(handlaggningUpdate);
      var putHandlaggningResponse = handlaggningClient.putHandlaggning(handlaggningUpdate.id(), putHandlaggningRequest);
      return handlaggningMapper.toHandlaggningUpdate(putHandlaggningResponse.getHandlaggning());
   }

}
