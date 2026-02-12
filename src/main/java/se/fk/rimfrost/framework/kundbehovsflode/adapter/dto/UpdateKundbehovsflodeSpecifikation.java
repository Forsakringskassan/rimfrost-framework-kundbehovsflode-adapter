package se.fk.rimfrost.framework.kundbehovsflode.adapter.dto;

import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface UpdateKundbehovsflodeSpecifikation
{

   UUID id();

   String version();

   String namn();

   String uppgiftsbeskrivning();

   Verksamhetslogik verksamhetslogik();

   Roll roll();

   String applikationsId();

   String applikationsversion();

   String url();

   UpdateKundbehovsflodeRegel regel();
}
