package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface Yrkande
{

   UUID id();

   int version();

   UUID erbjudandeId();

   OffsetDateTime yrkandeDatum();

   String yrkandeStatus();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   String avsikt();

   List<IndividYrkandeRoll> individYrkandeRoller();

   List<ProduceratResultat> produceradeResultat();

   @Value.Immutable
   public interface IndividYrkandeRoll
   {
      UUID individId();

      UUID yrkandeRollId();
   }

   @Value.Immutable
   public interface ProduceratResultat
   {
      UUID id();

      int version();

      OffsetDateTime resultatFrom();

      OffsetDateTime resultatTom();

      String typ();

      String data();
   }

}
