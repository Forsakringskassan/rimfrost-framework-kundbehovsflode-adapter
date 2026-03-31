package se.fk.rimfrost.framework.handlaggning.model;

import jakarta.annotation.Nullable;
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

   Yrkandestatus yrkandeStatus();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   String avsikt();

   List<IndividYrkandeRoll> individYrkandeRoller();

   List<ProduceratResultat> produceradeResultat();

   @Nullable
   Beslut beslut();

   @Value.Immutable
   public interface IndividYrkandeRoll
   {
      UUID individId();

      UUID yrkandeRollId();
   }

}
