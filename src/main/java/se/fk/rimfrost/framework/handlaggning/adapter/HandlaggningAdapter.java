package se.fk.rimfrost.framework.handlaggning.adapter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import se.fk.rimfrost.framework.handlaggning.exception.HandlaggningException;
import se.fk.rimfrost.framework.handlaggning.model.Handlaggning;
import se.fk.rimfrost.framework.handlaggning.model.HandlaggningUpdate;
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

   public Yrkande createYrkande(Yrkande yrkande) throws HandlaggningException
   {
      try
      {
         var postYrkandeRequest = handlaggningMapper.toPostYrkandeRequest(yrkande);
         var postYrkandeResponse = handlaggningClient.postYrkande(postYrkandeRequest);
         return handlaggningMapper.toYrkande(postYrkandeResponse.getYrkande());
      }
      catch (BadRequestException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.BAD_REQUEST,"Felaktig förfrågan vid skapande av yrkande", e);
      }
      catch (ProcessingException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.SERVICE_UNAVAILABLE,"Kunde inte nå handläggningsservice", e);
      }
      catch (WebApplicationException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.UNEXPECTED_ERROR, "Oväntat fel vid skapande av yrkande, status: " + e.getResponse().getStatus(), e);
      }
   }

   public Handlaggning createHandlaggning(UUID yrkandeId, UUID processinstansId, UUID handlaggningspecifikationId) throws HandlaggningException
   {
      try
      {
         var postHandlaggningRequest = handlaggningMapper.toPostHandlaggningRequest(yrkandeId, handlaggningspecifikationId);
         var postHandlaggningResponse = handlaggningClient.postHandlaggning(postHandlaggningRequest);
         return handlaggningMapper.toHandlaggning(postHandlaggningResponse.getHandlaggning());
      }
      catch (BadRequestException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.BAD_REQUEST, "Felaktig förfrågan vid skapande av handläggning för yrkande: " + yrkandeId, e);
      }
      catch (ProcessingException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.SERVICE_UNAVAILABLE, "Kunde inte nå handläggningsservice vid skapande av handläggning", e);
      }
      catch (WebApplicationException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.UNEXPECTED_ERROR, "Oväntat fel vid skapande av handläggning för yrkande: " + yrkandeId + ", status: " + e.getResponse().getStatus(), e);
      }
   }

   public Handlaggning readHandlaggning(UUID handlaggningId) throws HandlaggningException
   {
      try
      {
         var getHandlaggningResponse = handlaggningClient.getHandlaggning(handlaggningId);
         return handlaggningMapper.toHandlaggning(getHandlaggningResponse.getHandlaggning());
      }
      catch (NotFoundException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.NOT_FOUND, "Hittade ingen handläggning med id: " + handlaggningId, e);
      }
      catch (BadRequestException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.BAD_REQUEST, "Felaktig förfrågan vid hämtning av handläggning med id: " + handlaggningId, e);
      }
      catch (ProcessingException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.SERVICE_UNAVAILABLE, "Kunde inte nå handläggningstjänsten vid hämtning av handläggning", e);
      }
      catch (WebApplicationException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.UNEXPECTED_ERROR, "Oväntat fel vid hämtning av handläggning med id: " + handlaggningId + ", status: " + e.getResponse().getStatus(), e);
      }
   }

   public HandlaggningUpdate updateHandlaggning(HandlaggningUpdate handlaggningUpdate) throws HandlaggningException
   {
      try
      {
         var putHandlaggningRequest = handlaggningMapper.toPutHandlaggningRequest(handlaggningUpdate);
         var putHandlaggningResponse = handlaggningClient.putHandlaggning(handlaggningUpdate.id(), putHandlaggningRequest);
         return handlaggningMapper.toHandlaggningUpdate(putHandlaggningResponse.getHandlaggning());
      }
      catch (NotFoundException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.NOT_FOUND, "Ingen handläggning hittades med id: " + handlaggningUpdate.id(), e);
      }
      catch (BadRequestException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.BAD_REQUEST, "Felaktig förfrågan vid uppdatering av handläggning med id: " + handlaggningUpdate.id(), e);
      }
      catch (ProcessingException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.SERVICE_UNAVAILABLE, "Kunde inte nå handläggningstjänsten vid uppdatering av handläggning", e);
      }
      catch (WebApplicationException e)
      {
         throw new HandlaggningException(HandlaggningException.ErrorType.UNEXPECTED_ERROR, "Oväntat fel vid uppdatering av handläggning med id: " + handlaggningUpdate.id() + ", status: " + e.getResponse().getStatus(), e);
      }
   }

}
