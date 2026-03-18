package se.fk.rimfrost.framework.handlaggning.model;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.List;
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
   UUID utforarId();

   UppgiftStatus uppgiftStatus();

   UUID aktivitetId();

   FSSAinformation fSSAinformation();

   List<Underlag> underlag();

   UppgiftSpecifikation uppgiftSpecifikation();

}
