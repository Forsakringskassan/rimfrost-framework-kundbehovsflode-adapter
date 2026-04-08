package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface Beslut
{
   UUID id();

   int version();

   OffsetDateTime datum();

   Idtyp beslutsfattare();

   List<Beslutsrad> beslutsrader();
}
