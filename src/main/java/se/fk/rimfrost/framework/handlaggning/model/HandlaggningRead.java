package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value.Immutable
public interface HandlaggningRead
{

   UUID id();

   int version();

   Yrkande yrkande();

   UUID processInstansId();

   OffsetDateTime skapadTS();

   OffsetDateTime avslutadTS();

   UUID handlaggningspecifikationId();
}
