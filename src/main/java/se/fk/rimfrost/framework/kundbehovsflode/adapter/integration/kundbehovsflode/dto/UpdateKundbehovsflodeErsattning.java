package se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.kundbehovsflode.dto;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Ersattning.BeslutsutfallEnum;

import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeErsattning
{

   UUID id();

   @Nullable
   BeslutsutfallEnum beslutsutfall();

   @Nullable
   String avslagsanledning();

}
