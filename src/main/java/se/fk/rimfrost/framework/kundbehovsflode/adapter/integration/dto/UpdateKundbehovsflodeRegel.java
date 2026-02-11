package se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeRegel
{

   UUID id();

   String version();

   String namn();

   String beskrivning();

   UpdateKundbehovsflodeLagrum lagrum();

}
