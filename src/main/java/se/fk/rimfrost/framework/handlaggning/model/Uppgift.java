package se.fk.rimfrost.framework.handlaggning.model;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface Uppgift
{

   UUID id();

   int version();

   OffsetDateTime skapadTs();

   @Nullable
   OffsetDateTime utfordTs();

   @Nullable
   OffsetDateTime planeradTs();

   @Nullable
   Idtyp utforarId();

   String uppgiftStatus();

   UUID aktivitetId();

   String fSSAinformation();

   UppgiftSpecifikation uppgiftSpecifikation();

}
