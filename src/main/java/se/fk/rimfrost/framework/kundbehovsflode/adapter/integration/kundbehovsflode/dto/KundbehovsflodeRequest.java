package se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.kundbehovsflode.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface KundbehovsflodeRequest
{

   UUID kundbehovsflodeId();

}
