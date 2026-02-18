package se.fk.rimfrost.framework.kundbehovsflode.adapter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.fk.github.jaxrsclientfactory.JaxrsClientFactory;
import se.fk.github.jaxrsclientfactory.JaxrsClientOptionsBuilders;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.KundbehovsflodeRequest;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.KundbehovsflodeResponse;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.PatchErsattningRequest;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.PutKundbehovsflodeUppgiftRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.KundbehovsflodeControllerApi;
import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@SuppressWarnings("unused")
@ApplicationScoped
public class KundbehovsflodeAdapter
{

   @ConfigProperty(name = "kundbehovsflode.api.base-url")
   String kundbehovsflodeBaseUrl;

   private KundbehovsflodeControllerApi kundbehovsClient;

   @Inject
   KundbehovsflodeMapper kundbehovsflodemapper;

   @PostConstruct
   void init()
   {
      this.kundbehovsClient = new JaxrsClientFactory()
            .create(JaxrsClientOptionsBuilders.createClient(kundbehovsflodeBaseUrl, KundbehovsflodeControllerApi.class)
                  .build());
   }

   public KundbehovsflodeResponse getKundbehovsflodeInfo(KundbehovsflodeRequest kundbehovsflodeRequest)
   {
      var apiResponse = kundbehovsClient.getKundbehovsflode(kundbehovsflodeRequest.kundbehovsflodeId());
      return kundbehovsflodemapper.toKundbehovsflodeResponse(apiResponse);
   }

   public void putKundbehovsflode(PutKundbehovsflodeUppgiftRequest request)
   {
      var apiRequest = kundbehovsflodemapper.toPutKundbehovsflodeRequest(request);
      LOGGER.info("putKundbehovsflode " + request);
      try
      {
         kundbehovsClient.putKundbehovsflode(request.kundbehovsflodeId(), apiRequest);
      }
      catch (Throwable t)
      {
         t.printStackTrace();
         throw t;
      }
      LOGGER.info("putKundbehovsflode executed");
   }

   public void patchKundbehovsflode(PatchErsattningRequest request)
   {
      var apiRequest = kundbehovsflodemapper.toPatchKundbehovsflodeRequest(request);
      LOGGER.info("patchKundbehovsflode " + request);
      try
      {
         kundbehovsClient.patchKundbehovsflode(request.kundbehovsflodeId(), apiRequest);
      }
      catch (Throwable t)
      {
         t.printStackTrace();
         throw t;
      }
      LOGGER.info("patchKundbehovsflode executed");
   }
}
