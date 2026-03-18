package se.fk.rimfrost.framework.handlaggning.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Resultat
{
   String typ();

   String version();

   String data();
}
