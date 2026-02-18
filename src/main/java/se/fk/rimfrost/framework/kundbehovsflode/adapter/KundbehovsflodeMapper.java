package se.fk.rimfrost.framework.kundbehovsflode.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsutfall;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Roll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Verksamhetslogik;

import java.util.ArrayList;
import java.util.List;

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
               .beslutsutfall(ersattning.getBeslutsutfall().toString())
               .build());
      }
      return responseBuilder.build();
   }

   public PutKundbehovsflodeRequest toPutKundbehovsflodeRequest(PutKundbehovsflodeUppgiftRequest request)
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
      uppgiftspecifikation.setRoll(mapRoll(request.uppgift().specifikation().roll()));
      uppgiftspecifikation.setUppgiftbeskrivning(request.uppgift().specifikation().uppgiftsbeskrivning());
      uppgiftspecifikation.setUppgiftsGui(request.uppgift().specifikation().url());
      uppgiftspecifikation.setVerksamhetslogik(mapVerksamhetslogik(request.uppgift().specifikation().verksamhetslogik()));
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
      uppgift.setKundbehovsflodeId(request.kundbehovsflodeId());
      uppgift.setFsSAinformation(mapFssaInformation(request.uppgift().fsSAinformation()));
      uppgift.setSkapadTs(request.uppgift().skapadTs());
      uppgift.setUtfordTs(request.uppgift().utfordTs());
      uppgift.setUppgiftStatus(mapUppgiftStatus(request.uppgift().uppgiftStatus()));
      uppgift.setUtforarId(request.uppgift().utforarId());
      uppgift.setVersion(request.uppgift().version());
      uppgift.setUppgiftspecifikation(uppgiftspecifikation);
      uppgift.setUnderlag(underlagList);

      var putRequest = new PutKundbehovsflodeRequest();
      putRequest.setUppgift(uppgift);
      return putRequest;
   }

   public List<UpdateErsattning> toPatchKundbehovsflodeRequest(PatchErsattningRequest request)
   {
      var updateErsattningList = new ArrayList<UpdateErsattning>();
      for (var ersattning : request.ersattningar())
      {
         var updateErsattning = new UpdateErsattning();
         updateErsattning.setErsattningId(ersattning.ersattningId());
         updateErsattning.setBeslutsutfall(mapBeslutsutfall(ersattning.beslutsutfall()));
         updateErsattning.setAvslagsanledning(ersattning.avslagsanledning());
         updateErsattningList.add(updateErsattning);
      }
      return updateErsattningList;
   }

   private Beslutsutfall mapBeslutsutfall(se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.Beslutsutfall beslutsutfall) {
        return switch (beslutsutfall) {
            case JA -> Beslutsutfall.JA;
            case NEJ -> Beslutsutfall.NEJ;
            case FU -> Beslutsutfall.FU;
            default -> throw new InternalError("Could not map beslutsutfall: " + beslutsutfall);
        };
    }

   private FSSAinformation mapFssaInformation(
            se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.FSSAinformation fsSAinformation) {
        return switch (fsSAinformation) {
            case HANDLAGGNING_PAGAR -> FSSAinformation.HANDLAGGNING_PAGAR;
            case VANTAR_PA_INFO_FRAN_ANNAN_PART -> FSSAinformation.VANTAR_PA_INFO_FRAN_ANNAN_PART;
            case VANTAR_PA_INFO_FRAN_KUND -> FSSAinformation.VANTAR_PA_INFO_FRAN_KUND;
            default -> throw new InternalError("Could not map fssaInformation: " + fsSAinformation);
        };
    }

   private Verksamhetslogik mapVerksamhetslogik(
            se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.Verksamhetslogik verksamhetslogik) {
        return switch (verksamhetslogik) {
            case A -> Verksamhetslogik.A;
            case B -> Verksamhetslogik.B;
            case C -> Verksamhetslogik.C;
            default -> throw new InternalError("Could not map verksamhetslogik: " + verksamhetslogik);
        };
    }

   private Roll mapRoll(se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.Roll roll) {
        return switch (roll) {
            case AGARE -> Roll.AGARE;
            case ANSVARIG_HANDLAGGARE -> Roll.ANSVARIG_HANDLAGGARE;
            case DJUR -> Roll.DJUR;
            default -> throw new InternalError("Could not map roll: " + roll);
        };
    }

   private UppgiftStatus mapUppgiftStatus(se.fk.rimfrost.framework.kundbehovsflode.adapter.dto.UppgiftStatus uppgiftStatus) {
        return switch (uppgiftStatus) {
            case TILLDELAD -> TILLDELAD;
            case AVSLUTAD -> AVSLUTAD;
            case PLANERAD -> PLANERAD;
            default -> throw new InternalError("Could not map UppgiftStatus: " + uppgiftStatus);
        };
    }
}
