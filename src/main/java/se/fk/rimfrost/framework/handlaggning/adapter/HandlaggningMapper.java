package se.fk.rimfrost.framework.handlaggning.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.Ersattningstatus;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.HandlaggningResponse;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.ImmutableErsattning;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.ImmutableHandlaggningResponse;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.PatchErsattningRequest;
import se.fk.rimfrost.framework.handlaggning.adapter.dto.PutHandlaggningUppgiftRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.*;

import java.util.ArrayList;
import java.util.List;
import static se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus.*;

@ApplicationScoped
public class HandlaggningMapper
{
   public HandlaggningResponse toHandlaggningResponse(GetHandlaggningResponse apiResponse)
   {
      var responseBuilder = ImmutableHandlaggningResponse.builder()
            .personnummer(apiResponse.getHandlaggning().getYrkande().getYrkanderoll().getFirst().getIndivid().getId())
            .formanstyp(apiResponse.getHandlaggning().getYrkande().getFormanstyp())
            .handlaggningId(apiResponse.getHandlaggning().getId());

      for (var ersattning : apiResponse.getHandlaggning().getYrkande().getErsattning())
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

   public PutHandlaggningRequest toPutHandlaggningRequest(PutHandlaggningUppgiftRequest request)
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
      uppgift.setHandlaggningId(request.handlaggningId());
      uppgift.setFsSAinformation(mapFssaInformation(request.uppgift().fsSAinformation()));
      uppgift.setSkapadTs(request.uppgift().skapadTs());
      uppgift.setUtfordTs(request.uppgift().utfordTs());
      uppgift.setUppgiftStatus(mapUppgiftStatus(request.uppgift().uppgiftStatus()));
      uppgift.setUtforarId(request.uppgift().utforarId());
      uppgift.setVersion(request.uppgift().version());
      uppgift.setUppgiftspecifikation(uppgiftspecifikation);
      uppgift.setUnderlag(underlagList);

      var putRequest = new PutHandlaggningRequest();
      putRequest.setUppgift(uppgift);
      return putRequest;
   }

   public List<UpdateErsattning> toPatchHandlaggningRequest(PatchErsattningRequest request)
   {
      var updateErsattningList = new ArrayList<UpdateErsattning>();
      for (var ersattning : request.ersattningar())
      {
         var updateErsattning = new UpdateErsattning();
         updateErsattning.setErsattningId(ersattning.ersattningId());
         updateErsattning.setBeslutsutfall(mapBeslutsutfall(ersattning.beslutsutfall()));
         updateErsattning.setAvslagsanledning(ersattning.avslagsanledning());
         updateErsattning.ersattningsStatus(mapErsattningstatus(ersattning.ersattningstatus()));
         updateErsattningList.add(updateErsattning);
      }
      return updateErsattningList;
   }

   private Ersattningsstatus mapErsattningstatus(se.fk.rimfrost.framework.handlaggning.adapter.dto.Ersattningstatus ersattningstatus) {
            return switch (ersattningstatus) {
            case FASTSTALLT -> Ersattningsstatus.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> Ersattningsstatus.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> Ersattningsstatus.PLANERAT;
            case UNDER_UTREDNING -> Ersattningsstatus.UNDER_UTREDNING;
            case YRKAT -> Ersattningsstatus.YRKAT;
            default -> null;
        };
}

   private Beslutsutfall mapBeslutsutfall(se.fk.rimfrost.framework.handlaggning.adapter.dto.Beslutsutfall beslutsutfall) {
        return switch (beslutsutfall) {
            case JA -> Beslutsutfall.JA;
            case NEJ -> Beslutsutfall.NEJ;
            case FU -> Beslutsutfall.FU;
            default -> null;
        };
    }

   private FSSAinformation mapFssaInformation(
            se.fk.rimfrost.framework.handlaggning.adapter.dto.FSSAinformation fsSAinformation) {
        return switch (fsSAinformation) {
            case HANDLAGGNING_PAGAR -> FSSAinformation.HANDLAGGNING_PAGAR;
            case VANTAR_PA_INFO_FRAN_ANNAN_PART -> FSSAinformation.VANTAR_PA_INFO_FRAN_ANNAN_PART;
            case VANTAR_PA_INFO_FRAN_KUND -> FSSAinformation.VANTAR_PA_INFO_FRAN_KUND;
            default -> throw new InternalError("Could not map fssaInformation: " + fsSAinformation);
        };
    }

   private Verksamhetslogik mapVerksamhetslogik(
            se.fk.rimfrost.framework.handlaggning.adapter.dto.Verksamhetslogik verksamhetslogik) {
        return switch (verksamhetslogik) {
            case A -> Verksamhetslogik.A;
            case B -> Verksamhetslogik.B;
            case C -> Verksamhetslogik.C;
            default -> throw new InternalError("Could not map verksamhetslogik: " + verksamhetslogik);
        };
    }

   private Roll mapRoll(se.fk.rimfrost.framework.handlaggning.adapter.dto.Roll roll)
   {
      var mappedRoll = new Roll();
      mappedRoll.setId(roll.id());
      mappedRoll.setVersion(roll.version());
      mappedRoll.setNamn(roll.namn());
      return mappedRoll;
   }

   private UppgiftStatus mapUppgiftStatus(se.fk.rimfrost.framework.handlaggning.adapter.dto.UppgiftStatus uppgiftStatus) {
        return switch (uppgiftStatus) {
            case TILLDELAD -> TILLDELAD;
            case AVSLUTAD -> AVSLUTAD;
            case PLANERAD -> PLANERAD;
            default -> throw new InternalError("Could not map UppgiftStatus: " + uppgiftStatus);
        };
    }
}
