package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface Handlaggning
{

   UUID id();

   int version();

   Yrkande yrkande();

   UUID processInstansId();

   OffsetDateTime skapadTS();

   OffsetDateTime avslutadTS();

   UUID handlaggningspecifikationId();

   List<Underlag> underlag();

   Uppgift uppgift();
}
