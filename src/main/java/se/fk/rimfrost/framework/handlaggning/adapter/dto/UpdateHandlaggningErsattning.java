package se.fk.rimfrost.framework.handlaggning.adapter.dto;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface UpdateHandlaggningErsattning
{

   UUID ersattningId();

   @Nullable
   Beslutsutfall beslutsutfall();

   @Nullable
   String avslagsanledning();

   @Nullable
   Ersattningstatus ersattningstatus();

}
