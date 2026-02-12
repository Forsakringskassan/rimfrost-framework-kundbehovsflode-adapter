package se.fk.rimfrost.framework.kundbehovsflode.adapter.dto;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeErsattning
{

   UUID id();

   @Nullable
   Beslutsutfall beslutsutfall();

   @Nullable
   String avslagsanledning();

}
