package se.fk.rimfrost.framework.handlaggning.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.framework.handlaggning.model.*;
import se.fk.rimfrost.framework.handlaggning.model.Beslut;
import se.fk.rimfrost.framework.handlaggning.model.Beslutsrad;
import se.fk.rimfrost.framework.handlaggning.model.Handlaggning;
import se.fk.rimfrost.framework.handlaggning.model.HandlaggningUpdate;
import se.fk.rimfrost.framework.handlaggning.model.Idtyp;
import se.fk.rimfrost.framework.handlaggning.model.ProduceratResultat;
import se.fk.rimfrost.framework.handlaggning.model.ProduceratResultatRef;
import se.fk.rimfrost.framework.handlaggning.model.Underlag;
import se.fk.rimfrost.framework.handlaggning.model.Uppgift;
import se.fk.rimfrost.framework.handlaggning.model.UppgiftSpecifikation;
import se.fk.rimfrost.framework.handlaggning.model.Yrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.*;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class HandlaggningMapper
{

   // ---------------------
   //
   // Yrkande
   //
   // ---------------------

   //
   // to API
   //

   public PostYrkandeRequest toPostYrkandeRequest(Yrkande yrkande)
   {
      var postYrkandeRequest = new PostYrkandeRequest();
      postYrkandeRequest.setErbjudandeId(yrkande.erbjudandeId());
      postYrkandeRequest.setYrkandeFrom(yrkande.yrkandeFrom());
      postYrkandeRequest.setYrkandeTom(yrkande.yrkandeTom());
      postYrkandeRequest.setIndividYrkandeRoller(toApiIndividYrkandeRoller(yrkande.individYrkandeRoller()));
      postYrkandeRequest.setProduceradeResultat(toApiProduceradeResultat(yrkande.produceradeResultat()));
      return postYrkandeRequest;
   }

   private List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll> toApiIndividYrkandeRoller(
         List<Yrkande.IndividYrkandeRoll> individYrkandeRoller)
   {
      return individYrkandeRoller.stream()
            .map(a -> {
               var b = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll();
               b.setIndivid(toApiIdtyp(a.individ()));
               b.setYrkandeRollId(a.yrkandeRollId());
               return b;
            })
            .toList();
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp toApiIdtyp(Idtyp idtyp)
   {
      if (idtyp == null)
      {
         return null;
      }

      se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp apiIdTyp = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp();
      apiIdTyp.setTypId(idtyp.typId());
      apiIdTyp.setVarde(idtyp.varde());

      return apiIdTyp;
   }

   private List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat> toApiProduceradeResultat(
         List<ProduceratResultat> produceradeResultat)
   {
      return produceradeResultat.stream()
            .map(a -> {
               var b = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat();
               b.setId(a.id());
               b.setVersion(a.version());
               b.setFrom(a.resultatFrom());
               b.setTom(a.resultatTom());
               b.setYrkandestatus(a.yrkandeStatus());
               b.setAvslagsanledning(a.avslagsanledning());
               b.setTyp(a.typ());
               b.setData(a.data());
               return b;
            })
            .toList();
   }

   private List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultatRef> toApiProduceradeResultatRef(
         List<ProduceratResultatRef> produceradeResultatRef)
   {
      return produceradeResultatRef.stream()
            .map(a -> {
               var b = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultatRef();
               b.setId(a.id());
               b.setVersion(a.version());
               return b;
            })
            .toList();
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad toApiBeslutsrad(Beslutsrad beslutsrad)
   {
      var apiBeslutsrad = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad();
      apiBeslutsrad.setId(beslutsrad.id());
      apiBeslutsrad.setVersion(beslutsrad.version());
      apiBeslutsrad.setBeslutsTyp(beslutsrad.beslutsTyp());
      apiBeslutsrad.setBeslutsUtfall(beslutsrad.beslutsUtfall());
      apiBeslutsrad.setAvslutsTyp(beslutsrad.avslutsTyp());
      apiBeslutsrad.setProduceradeResultatRef(toApiProduceradeResultatRef(beslutsrad.produceradeResultatRef()));
      return apiBeslutsrad;
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut toApiBeslut(Beslut beslut)
   {
      var apiBeslut = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut();
      apiBeslut.setId(beslut.id());
      apiBeslut.setVersion(beslut.version());
      apiBeslut.setBeslutsfattare(toApiIdtyp(beslut.beslutsfattare()));
      apiBeslut.setDatum(beslut.datum());
      apiBeslut.setBeslutsrader(beslut.beslutsrader().stream().map(this::toApiBeslutsrad).toList());

      return apiBeslut;
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande toApiYrkande(Yrkande yrkande)
   {
      var apiYrkande = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande();
      apiYrkande.setId(yrkande.id());
      apiYrkande.setVersion(yrkande.version());
      apiYrkande.setErbjudandeId(yrkande.erbjudandeId());
      apiYrkande.setYrkandedatum(yrkande.yrkandeFrom());
      apiYrkande.setYrkandestatus(yrkande.yrkandeStatus());
      apiYrkande.setYrkandeFrom(yrkande.yrkandeFrom());
      apiYrkande.setYrkandeTom(yrkande.yrkandeTom());
      apiYrkande.setAvsikt(yrkande.avsikt());
      apiYrkande.setIndividYrkandeRoller(toApiIndividYrkandeRoller(yrkande.individYrkandeRoller()));
      apiYrkande.setProduceradeResultat(toApiProduceradeResultat(yrkande.produceradeResultat()));

      var beslut = yrkande.beslut();
      if (beslut != null)
      {
         apiYrkande.setBeslut(toApiBeslut(beslut));
      }

      return apiYrkande;
   }

   // to model

   public Yrkande toYrkande(se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande apiYrkande)
   {
      return ImmutableYrkande.builder()
            .id(apiYrkande.getId())
            .version(apiYrkande.getVersion())
            .erbjudandeId(apiYrkande.getErbjudandeId())
            .yrkandeDatum(apiYrkande.getYrkandedatum())
            .yrkandeStatus(apiYrkande.getYrkandestatus())
            .yrkandeFrom(apiYrkande.getYrkandeFrom())
            .yrkandeTom(apiYrkande.getYrkandeTom())
            .avsikt(String.valueOf(apiYrkande.getAvsikt()))
            .individYrkandeRoller(toIndividYrkandeRoller(apiYrkande.getIndividYrkandeRoller()))
            .produceradeResultat(toProduceradeResultat(apiYrkande.getProduceradeResultat()))
            .build();
   }

   private List<ImmutableIndividYrkandeRoll> toIndividYrkandeRoller(
         List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll> apiIndividYrkandeRoller)
   {
      return apiIndividYrkandeRoller.stream()
            .map(a -> ImmutableIndividYrkandeRoll.builder()
                  .individ(toIdtyp(a.getIndivid()))
                  .yrkandeRollId(a.getYrkandeRollId())
                  .build())
            .toList();
   }

   private Idtyp toIdtyp(se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp apiIdTyp)
   {
      if (apiIdTyp == null)
      {
         return null;
      }

      return ImmutableIdtyp.builder()
            .typId(apiIdTyp.getTypId())
            .varde(apiIdTyp.getVarde())
            .build();
   }

   private List<ImmutableProduceratResultat> toProduceradeResultat(
         List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat> apiProduceradeResultat)
   {
      return apiProduceradeResultat.stream()
            .map(a -> ImmutableProduceratResultat.builder()
                  .id(a.getId())
                  .version(a.getVersion())
                  .resultatFrom(a.getFrom())
                  .resultatTom(a.getTom())
                  .yrkandeStatus(a.getYrkandestatus())
                  .avslagsanledning(a.getAvslagsanledning())
                  .typ(a.getTyp())
                  .data(a.getData())
                  .build())
            .toList();
   }

   // ---------------------
   //
   // Handläggning
   //
   // ---------------------

   //
   // to API
   //

   public PostHandlaggningRequest toPostHandlaggningRequest(UUID yrkandeId, UUID handlaggningspecifikationId)
   {
      var postHandlaggningRequest = new PostHandlaggningRequest();
      postHandlaggningRequest.setYrkandeId(yrkandeId);
      postHandlaggningRequest.setHandlaggningspecifikationId(handlaggningspecifikationId);
      return postHandlaggningRequest;
   }

   public PutHandlaggningRequest toPutHandlaggningRequest(HandlaggningUpdate handlaggningUpdate)
   {
      var putHandlaggningRequest = new PutHandlaggningRequest();
      putHandlaggningRequest.setHandlaggning(toApiHandlaggningUpdate(handlaggningUpdate));
      return putHandlaggningRequest;
   }

   public se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate toApiHandlaggningUpdate(
         HandlaggningUpdate handlaggningUpdate)
   {
      var apiHandlaggningUpdate = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate();
      apiHandlaggningUpdate.setId(handlaggningUpdate.id());
      apiHandlaggningUpdate.setVersion(handlaggningUpdate.version());
      apiHandlaggningUpdate.setYrkande(toApiYrkande(handlaggningUpdate.yrkande()));
      apiHandlaggningUpdate.setProcessinstansId(handlaggningUpdate.processInstansId());
      apiHandlaggningUpdate.setSkapadTS(handlaggningUpdate.skapadTS());
      apiHandlaggningUpdate.setAvslutadTS(handlaggningUpdate.avslutadTS());
      apiHandlaggningUpdate.setHandlaggningspecifikationId(handlaggningUpdate.handlaggningspecifikationId());
      apiHandlaggningUpdate.setUnderlag(toApiUnderlagList(handlaggningUpdate.underlag()));
      apiHandlaggningUpdate.setUppgift(toApiUppgift(handlaggningUpdate.uppgift()));
      return apiHandlaggningUpdate;
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag toApiUnderlag(Underlag underlag)
   {
      var apiUnderlag = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag();
      apiUnderlag.setTyp(underlag.typ());
      apiUnderlag.setVersion(underlag.version());
      apiUnderlag.setData(underlag.data());
      return apiUnderlag;
   }

   private List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag> toApiUnderlagList(
         List<Underlag> underlagList)
   {
      return underlagList.stream()
            .map(this::toApiUnderlag)
            .toList();
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift toApiUppgift(Uppgift uppgift)
   {
      var apiUppgift = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift();
      apiUppgift.setId(uppgift.id());
      apiUppgift.setVersion(uppgift.version());
      apiUppgift.setSkapadTs(uppgift.skapadTs());
      apiUppgift.setUtfordTs(uppgift.utfordTs());
      apiUppgift.setPlaneradTs(uppgift.planeradTs());
      apiUppgift.setUtforarId(toApiIdtyp(uppgift.utforarId()));
      apiUppgift.setAktivitetId(uppgift.aktivitetId());
      apiUppgift.setUppgiftspecifikation(toApiUppgiftSpecifikation(uppgift.uppgiftSpecifikation()));
      apiUppgift.setUppgiftStatus(uppgift.uppgiftStatus());
      apiUppgift.setFsSAinformation(uppgift.fSSAinformation());
      return apiUppgift;
   }

   private se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation toApiUppgiftSpecifikation(
         UppgiftSpecifikation uppgiftSpecifikation)
   {
      var apiUppgiftSpecifikation = new se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation();
      apiUppgiftSpecifikation.setId(uppgiftSpecifikation.id());
      apiUppgiftSpecifikation.setVersion(uppgiftSpecifikation.version());
      return apiUppgiftSpecifikation;
   }

   //
   // to model
   //

   private Underlag toUnderlag(se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag apiUnderlag)
   {
      return ImmutableUnderlag.builder()
            .typ(apiUnderlag.getTyp())
            .version(apiUnderlag.getVersion())
            .data(apiUnderlag.getData())
            .build();
   }

   private List<Underlag> toUnderlagList(
         List<se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag> apiUnderlagList)
   {
      return apiUnderlagList.stream()
            .map(this::toUnderlag)
            .toList();
   }

   private UppgiftSpecifikation toUppgiftSpecifikation(
         se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation apiUppgiftSpecifikation)
   {
      return ImmutableUppgiftSpecifikation.builder()
            .id(apiUppgiftSpecifikation.getId())
            .version(apiUppgiftSpecifikation.getVersion()).build();
   }

   private Uppgift toUppgift(se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift apiUppgift)
   {
      return ImmutableUppgift.builder()
            .id(apiUppgift.getId())
            .version(apiUppgift.getVersion())
            .skapadTs(apiUppgift.getSkapadTs())
            .utfordTs(apiUppgift.getUtfordTs())
            .planeradTs(apiUppgift.getPlaneradTs())
            .utforarId(toIdtyp(apiUppgift.getUtforarId()))
            .aktivitetId(apiUppgift.getAktivitetId())
            .uppgiftSpecifikation(toUppgiftSpecifikation(apiUppgift.getUppgiftspecifikation()))
            .uppgiftStatus(apiUppgift.getUppgiftStatus())
            .fSSAinformation(apiUppgift.getFsSAinformation())
            .build();
   }

   public HandlaggningUpdate toHandlaggningUpdate(
         se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate apiHandlaggningUpdate)
   {
      return ImmutableHandlaggningUpdate.builder()
            .id(apiHandlaggningUpdate.getId())
            .version(apiHandlaggningUpdate.getVersion())
            .yrkande(toYrkande(apiHandlaggningUpdate.getYrkande()))
            .processInstansId(apiHandlaggningUpdate.getProcessinstansId())
            .skapadTS(apiHandlaggningUpdate.getSkapadTS())
            .avslutadTS(apiHandlaggningUpdate.getAvslutadTS())
            .handlaggningspecifikationId(apiHandlaggningUpdate.getHandlaggningspecifikationId())
            .underlag(toUnderlagList(apiHandlaggningUpdate.getUnderlag()))
            .uppgift(toUppgift(apiHandlaggningUpdate.getUppgift()))
            .build();

   }

   public Handlaggning toHandlaggning(
         se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning apiHandlaggning)
   {
      return ImmutableHandlaggning.builder()
            .id(apiHandlaggning.getId())
            .version(apiHandlaggning.getVersion())
            .yrkande(toYrkande(apiHandlaggning.getYrkande()))
            .processInstansId(apiHandlaggning.getProcessinstansId())
            .skapadTS(apiHandlaggning.getSkapadTS())
            .avslutadTS(apiHandlaggning.getAvslutadTS())
            .handlaggningspecifikationId(apiHandlaggning.getHandlaggningspecifikationId())
            .build();
   }

}
