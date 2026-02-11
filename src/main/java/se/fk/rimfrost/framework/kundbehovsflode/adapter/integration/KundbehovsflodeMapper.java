package se.fk.rimfrost.framework.kundbehovsflode.adapter.integration;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.dto.ImmutableErsattning;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.dto.ImmutableKundbehovsflodeResponse;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.dto.KundbehovsflodeResponse;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.dto.UpdateKundbehovsflodeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.*;
import java.util.ArrayList;
import static se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus.*;

@ApplicationScoped
public class KundbehovsflodeMapper
{
   public KundbehovsflodeResponse toKundbehovsflodeResponse(GetKundbehovsflodeResponse apiResponse)
   {
      var responseBuilder = ImmutableKundbehovsflodeResponse.builder()
            .personnummer(apiResponse.getKundbehovsflode().getKundbehov().getKundbehovsroll().getFirst().getIndivid().getId())
            .formanstyp(apiResponse.getKundbehovsflode().getKundbehov().getFormanstyp())
            .kundbehovsflodeId(apiResponse.getKundbehovsflode().getId());

      for (var ersattning : apiResponse.getKundbehovsflode().getKundbehov().getErsattning())
      {
         responseBuilder.addErsattning(ImmutableErsattning.builder()
               .belopp(ersattning.getBelopp())
               .berakningsgrund(ersattning.getBerakningsgrund().ordinal())
               .ersattningsId(ersattning.getId())
               .ersattningsTyp(ersattning.getErsattningstyp().toString())
               .franOchMed(ersattning.getProduceratResultat().getFrom().toLocalDate())
               .tillOchMed(ersattning.getProduceratResultat().getTom().toLocalDate())
               .omfattningsProcent(ersattning.getOmfattning())
               .build());
      }
      return responseBuilder.build();
   }

   public PutKundbehovsflodeRequest toKundbehovsflodeRequest(UpdateKundbehovsflodeRequest request,
         GetKundbehovsflodeResponse apiResponse)
   {
      var lagrum = new Lagrum();
      lagrum.setId(request.uppgift().specifikation().regel().lagrum().id());
      lagrum.setVersion(request.uppgift().specifikation().regel().lagrum().version());
      lagrum.setForfattning(request.uppgift().specifikation().regel().lagrum().forfattning());
      lagrum.setGiltigFrom(request.uppgift().specifikation().regel().lagrum().giltigFrom());
      lagrum.setGiltigTom(request.uppgift().specifikation().regel().lagrum().giltigTom());
      lagrum.setKapitel(request.uppgift().specifikation().regel().lagrum().kapitel());
      lagrum.setParagraf(request.uppgift().specifikation().regel().lagrum().paragraf());
      lagrum.setPunkt(request.uppgift().specifikation().regel().lagrum().punkt());
      lagrum.setStycke(request.uppgift().specifikation().regel().lagrum().stycke());

      var regel = new Regel();
      regel.setId(request.uppgift().specifikation().regel().id());
      regel.setVersion(request.uppgift().specifikation().regel().version());
      regel.setLagrum(lagrum);

      var uppgiftspecifikation = new Uppgiftspecifikation();
      uppgiftspecifikation.setId(request.uppgift().specifikation().id());
      uppgiftspecifikation.setApplikationsId(request.uppgift().specifikation().applikationsId());
      uppgiftspecifikation.setApplikationsVersion(request.uppgift().specifikation().applikationsversion());
      uppgiftspecifikation.setNamn(request.uppgift().specifikation().namn());
      uppgiftspecifikation.setRoll(request.uppgift().specifikation().roll());
      uppgiftspecifikation.setUppgiftbeskrivning(request.uppgift().specifikation().uppgiftsbeskrivning());
      uppgiftspecifikation.setUppgiftsGui(request.uppgift().specifikation().url());
      uppgiftspecifikation.setVerksamhetslogik(request.uppgift().specifikation().verksamhetslogik());
      uppgiftspecifikation.setVersion(request.uppgift().specifikation().version());
      uppgiftspecifikation.setRegel(regel);

      var underlagList = new ArrayList<Underlag>();
      for (var underlag : request.underlag())
      {
         var underlagitem = new Underlag();
         underlagitem.typ(underlag.typ());
         underlagitem.version(underlag.version());
         underlagitem.data(underlag.data());
         underlagList.add(underlagitem);
      }

      var uppgift = new Uppgift();
      uppgift.setId(request.uppgift().id());
      uppgift.setFsSAinformation(request.uppgift().fsSAinformation());
      uppgift.setSkapadTs(request.uppgift().skapadTs());
      uppgift.setUtfordTs(request.uppgift().utfordTs());
      uppgift.setUppgiftStatus(mapUppgiftStatus(request.uppgift().uppgiftStatus()));
      uppgift.setUtforarId(request.uppgift().utforarId());
      uppgift.setVersion(request.uppgift().version());
      uppgift.setUppgiftspecifikation(uppgiftspecifikation);
      uppgift.setUnderlag(underlagList);

      var ersattningar = apiResponse.getKundbehovsflode().getKundbehov().getErsattning();

      for (var ersattning : request.ersattningar())
      {
         var ersattningItem = ersattningar.stream().filter(e -> e.getId().equals(ersattning.id())).findFirst().get();
         ersattningItem.setAvslagsanledning(ersattning.avslagsanledning());
         ersattningItem.setBeslutsutfall(ersattning.beslutsutfall());
      }

      var kundbehovflode = apiResponse.getKundbehovsflode();
      var kundbehov = kundbehovflode.getKundbehov();
      kundbehov.setErsattning(ersattningar);
      kundbehovflode.setKundbehov(kundbehov);
      uppgift.setKundbehovsflode(kundbehovflode);

      var putRequest = new PutKundbehovsflodeRequest();
      putRequest.setUppgift(uppgift);
      return putRequest;
   }

   private UppgiftStatus mapUppgiftStatus(
         se.fk.rimfrost.framework.regel.logic.dto.UppgiftStatus uppgiftStatus)
   {
       return switch (uppgiftStatus) {
           case TILLDELAD -> TILLDELAD;
           case AVSLUTAD -> AVSLUTAD;
           case PLANERAD -> PLANERAD;
           default -> throw new InternalError("Could not map UppgiftStatus: " + uppgiftStatus);
       };
   }
}
