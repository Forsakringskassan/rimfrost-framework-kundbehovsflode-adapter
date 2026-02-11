package se.fk.rimfrost.framework.kundbehovsflode.adapter.integration.kundbehovsflode.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface UpdateKundbehovsflodeUnderlag
{

   String typ();

   String version();

   String data();

}
