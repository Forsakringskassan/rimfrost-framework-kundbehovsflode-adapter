package se.fk.rimfrost.framework.handlaggning.adapter.dto;

import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface Roll
{
   UUID id();

   String version();

   String namn();

}
