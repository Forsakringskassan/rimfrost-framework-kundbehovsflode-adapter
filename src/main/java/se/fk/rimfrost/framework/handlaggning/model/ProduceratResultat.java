package se.fk.rimfrost.framework.handlaggning.model;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface ProduceratResultat
{
   UUID id();

   int version();

   OffsetDateTime resultatFrom();

   OffsetDateTime resultatTom();

   String yrkandeStatus();

   @Nullable
   String avslagsanledning();

   String typ();

   String data();
}
