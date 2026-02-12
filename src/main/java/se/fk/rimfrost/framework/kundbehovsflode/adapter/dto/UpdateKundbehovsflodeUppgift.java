package se.fk.rimfrost.framework.kundbehovsflode.adapter.dto;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeUppgift
{
   UUID id();

   String version();

   OffsetDateTime skapadTs();

   @Nullable
   OffsetDateTime utfordTs();

   @Nullable
   OffsetDateTime planeradTs();

   @Nullable
   UUID utforarId();

   UppgiftStatus uppgiftStatus();

   String aktivitet();

   FSSAinformation fsSAinformation();

   UpdateKundbehovsflodeSpecifikation specifikation();
}
