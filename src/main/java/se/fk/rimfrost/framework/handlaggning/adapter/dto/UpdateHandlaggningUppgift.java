package se.fk.rimfrost.framework.handlaggning.adapter.dto;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface UpdateHandlaggningUppgift
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

   UUID aktivitetId();

   FSSAinformation fsSAinformation();

   UpdateHandlaggningSpecifikation specifikation();
}
