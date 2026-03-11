package se.fk.rimfrost.framework.handlaggning.adapter.dto;

import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface UpdateHandlaggningSpecifikation
{

   UUID id();

   String version();

   String namn();

   String uppgiftsbeskrivning();

   Verksamhetslogik verksamhetslogik();

   String roll();

   String applikationsId();

   String applikationsversion();

   String url();

   UpdateHandlaggningRegel regel();
}
